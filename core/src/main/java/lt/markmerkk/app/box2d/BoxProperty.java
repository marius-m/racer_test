package lt.markmerkk.app.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import org.jetbrains.annotations.NotNull;

public final class BoxProperty {
   @NotNull
   private final Body body;
   @NotNull
   private final World world;
   private final float width;
   private final float height;
   @NotNull
   private final Vector2 position;

   public BoxProperty(
           @NotNull World world,
           float width,
           float height,
           @NotNull Vector2 position
   ) {
      super();
      this.world = world;
      this.width = width;
      this.height = height;
      this.position = position;

      BodyDef bodyDef = new BodyDef();
      bodyDef.position.set(this.position);
      bodyDef.angle = 0.0F;
      bodyDef.fixedRotation = true;

      body = this.world.createBody(bodyDef);

      FixtureDef fixtureDef = new FixtureDef();
      PolygonShape boxShape = new PolygonShape();
      boxShape.setAsBox(this.width / (float)2, this.height / (float)2);
      fixtureDef.shape = (Shape)boxShape;
      fixtureDef.restitution = 0.7F;
      body.createFixture(fixtureDef);
      boxShape.dispose();
   }
}
