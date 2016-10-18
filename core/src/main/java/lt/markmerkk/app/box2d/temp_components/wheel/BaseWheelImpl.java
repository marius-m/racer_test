package lt.markmerkk.app.box2d.temp_components.wheel;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import lt.markmerkk.app.box2d.Car;
import org.jetbrains.annotations.NotNull;

public abstract class BaseWheelImpl implements Wheel {
   @NotNull
   private final Body body;
   @NotNull
   private final World world;
   @NotNull
   private final Car car;
   private float posX;
   private float posY;
   private final float width;
   private final float height;
   private final boolean powered;

   @NotNull
   public Body getBody() {
      return this.body;
   }

   public abstract void initJoint(@NotNull World var1, @NotNull Car var2);

   @NotNull
   public Vector2 localVelocity() {
      return body.getLocalVector(body.getLinearVelocityFromLocalPoint(body.getPosition()));
   }

   @NotNull
   public Vector2 directionVector() {
      Vector2 directionVector;
      if(this.localVelocity().y > (float)0) {
         directionVector = new Vector2(0.0F, 1.0F);
      } else {
         directionVector = new Vector2(0.0F, -1.0F);
      }

      return directionVector.rotate((float) Math.toDegrees((double)this.getBody().getAngle()));
   }

   public void changeAngle(float angle) {
      body.setTransform(
              body.getPosition(),
              car.getBody().getAngle() + (float) Math.toRadians(angle)
      );
   }

   public void killSidewayVector() {
      body.setLinearVelocity(killedLocalVelocity());
   }

   private final Vector2 killedLocalVelocity() {
      Vector2 velocity = this.getBody().getLinearVelocity();
      Vector2 sidewaysAxis = this.directionVector();
      float dotprod = velocity.dot(sidewaysAxis);
      return new Vector2(sidewaysAxis.x * dotprod, sidewaysAxis.y * dotprod);
   }

   @NotNull
   public World getWorld() {
      return this.world;
   }

   @NotNull
   public Car getCar() {
      return this.car;
   }

   public float getPosX() {
      return this.posX;
   }

   public float getPosY() {
      return this.posY;
   }

   public float getWidth() {
      return this.width;
   }

   public float getHeight() {
      return this.height;
   }

   public BaseWheelImpl(
           @NotNull World world,
           @NotNull Car car,
           float posX,
           float posY,
           float width,
           float height,
           boolean powered
   ) {
      this.world = world;
      this.car = car;
      this.posX = posX;
      this.posY = posY;
      this.width = width;
      this.height = height;
      this.powered = powered;

      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.DynamicBody;
      bodyDef.position.set(this.getCar().getBody().getWorldPoint(new Vector2(this.getPosX(), this.getPosY())));
      bodyDef.angle = this.getCar().getBody().getAngle();

      this.body = world.createBody(bodyDef);

      FixtureDef fixtureDef = new FixtureDef();
      fixtureDef.density = 1.0F;
      fixtureDef.isSensor = true;

      PolygonShape wheelShape = new PolygonShape();

      wheelShape.setAsBox(this.getWidth() / (float)2, this.getHeight() / (float)2);
      fixtureDef.shape = wheelShape;
      body.createFixture(fixtureDef);
      wheelShape.dispose();

      initJoint(this.getWorld(), this.getCar());
   }
}
