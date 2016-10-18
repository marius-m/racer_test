package lt.markmerkk.app.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import java.util.*;

import lt.markmerkk.app.box2d.temp_components.wheel.RevolvingWheelImpl;
import lt.markmerkk.app.box2d.temp_components.wheel.Wheel;
import lt.markmerkk.app.box2d.temp_components.wheel.WheelImpl;
import org.jetbrains.annotations.NotNull;

public final class Car {
   private static final double TWO_PI = (double)2 * 3.141592653589793D;
   public static final int STEER_NONE = 0;
   public static final int STEER_LEFT = 1;
   public static final int STEER_RIGHT = 2;
   public static final int ACC_NONE = 0;
   public static final int ACC_FORWARD = 1;
   public static final int ACC_BACKWARD = 2;

   private final float width;
   private final float height;
   private final float maxSteerAngle;
   private final float minSteerAngle;
   private final float power;
   private final float maxSpeed;
   private final float angle;
   private int steer;
   private int accelerate;
   private float wheelAngle;
   private float speed;

   @NotNull
   private final Texture texture;
   @NotNull
   private final TextureRegion textureRegion;
   @NotNull
   private final Sprite sprite;
   @NotNull
   private final Body body;
   @NotNull
   public List<Wheel> wheels;

   @NotNull
   private final World world;
   @NotNull
   private final Vector2 position;

   public Car(
           @NotNull World world,
           @NotNull Vector2 position
   ) {
      super();
      this.world = world;
      this.position = position;
      this.texture = new Texture(Gdx.files.internal("data/car_small.png"));
      this.textureRegion = new TextureRegion(this.texture, 64, 37);
      this.sprite = new Sprite(this.texture);
      this.width = 1.0F;
      this.height = 2.0F;
      this.maxSteerAngle = 30.0F;
      this.minSteerAngle = 15.0F;
      this.power = 5.0F;
      this.maxSpeed = 20.0F;
      this.angle = (float)3.141592653589793D;

      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.DynamicBody;
      bodyDef.position.set(this.position);
      bodyDef.angle = this.angle;
      body = this.world.createBody(bodyDef);

      FixtureDef fixtureDef = new FixtureDef();
      fixtureDef.density = 1.0F;
      fixtureDef.friction = 0.1F;
      fixtureDef.restitution = 0.2F;

      PolygonShape carShape = new PolygonShape();
      carShape.setAsBox(this.width / (float)2, this.height / (float)2);

      fixtureDef.shape = (Shape)carShape;
      this.body.createFixture(fixtureDef);

      this.wheels = new ArrayList<Wheel>();
      this.wheels.add((Wheel)new RevolvingWheelImpl(this.world, this, -0.5F, -0.6F, 0.2F, 0.4F));
      this.wheels.add((Wheel)new RevolvingWheelImpl(this.world, this, 0.5F, -0.6F, 0.2F, 0.4F));
      this.wheels.add((Wheel)new WheelImpl(this.world, this, -0.5F, 0.6F, 0.2F, 0.4F));
      this.wheels.add((Wheel)new WheelImpl(this.world, this, 0.5F, 0.6F, 0.2F, 0.4F));

   }

   @NotNull
   public final Texture getTexture() {
      return this.texture;
   }

   @NotNull
   public final Sprite getSprite() {
      return this.sprite;
   }

   @NotNull
   public final Body getBody() {
      return this.body;
   }

   public final void setSteer(int var1) {
      this.steer = var1;
   }

   public final void setAccelerate(int var1) {
      this.accelerate = var1;
   }

   public final void setSpeed(float value) { // todo : check this ?
      Vector2 velocity = body.getLinearVelocity();
      velocity = velocity.nor();
      velocity = new Vector2(
              velocity.x * (this.speed * 1000.0F / 3600.0F),
              velocity.y * (this.speed * 1000.0F / 3600.0F)
      );
      body.setLinearVelocity(velocity);
   }

   public final float getSpeedKMH() {
      Vector2 velocity = this.body.getLinearVelocity();
      float len = velocity.len();
      return len / (float)1000 * (float)3600;
   }

   @NotNull
   public final Vector2 getLocalVelocity() {
      return body.getLocalVector(this.body.getLinearVelocityFromLocalPoint(new Vector2(0.0F, 0.0F)));
   }

   public final void update(float deltaTime) {
      for (Wheel wheel : wheels) {
         wheel.killSidewayVector();
      }

      float increase = minSteerAngle * deltaTime * 5f;
      switch (steer) {
         case STEER_LEFT:
            this.wheelAngle = Math.min(
                    Math.max(wheelAngle, 0f) + increase,
                    maxSteerAngle
            );
            break;
         case STEER_RIGHT:
            this.wheelAngle = Math.max(
                    Math.min(wheelAngle, 0f) - increase,
                    -maxSteerAngle
            );
            break;
         case STEER_NONE:
            this.wheelAngle = 0.0f;
            break;
      }
      for (Wheel wheel : wheels) {
         if (wheel instanceof RevolvingWheelImpl) {
            wheel.changeAngle(this.wheelAngle);
         }
      }

      Vector2 accelerateVector;
      if(accelerate == ACC_FORWARD && this.getSpeedKMH() < this.maxSpeed) {
         accelerateVector = new Vector2(0.0F, -1.0F);
      } else if(this.accelerate == ACC_BACKWARD) {
         if(this.getLocalVelocity().y < (float)0) {
            accelerateVector = new Vector2(0.0F, 1.3F);
         } else {
            accelerateVector = new Vector2(0.0F, 0.7F);
         }
      } else if(this.accelerate == ACC_NONE) {
         accelerateVector = new Vector2(0.0F, 0.0F);
         if(this.getSpeedKMH() < (float)7) {
            this.setSpeed(0.0F);
         } else if(getLocalVelocity().y < (float)0) {
            accelerateVector = new Vector2(0.0F, 0.7F);
         } else if(getLocalVelocity().y > (float)0) {
            accelerateVector = new Vector2(0.0F, -0.7F);
         }
      } else {
         accelerateVector = new Vector2(0.0F, 0.0F);
      }

      Vector2 forceVector2 = new Vector2(
              this.power * accelerateVector.x,
              this.power * accelerateVector.y
      );
      for (Wheel wheel : wheels) {
         if (wheel instanceof RevolvingWheelImpl) {
            Body wheelBody = wheel.getBody();
            Vector2 bodyCenter = wheelBody.getWorldCenter();
            wheelBody.applyForce(
                    wheelBody.getWorldVector(
                            new Vector2(forceVector2.x, forceVector2.y)
                    ),
                    bodyCenter,
                    true
            );
         }
      }
   }

}
