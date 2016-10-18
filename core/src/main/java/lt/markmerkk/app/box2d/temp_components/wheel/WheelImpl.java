package lt.markmerkk.app.box2d.temp_components.wheel;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import lt.markmerkk.app.box2d.Car;
import org.jetbrains.annotations.NotNull;

public final class WheelImpl extends BaseWheelImpl {
   public void initJoint(
           @NotNull World world,
           @NotNull Car car
   ) {
      PrismaticJointDef jointdef = new PrismaticJointDef();
      jointdef.initialize(car.getBody(), this.getBody(), this.getBody().getWorldCenter(), new Vector2(1.0F, 0.0F));
      jointdef.enableLimit = true;
      jointdef.lowerTranslation = 0.0F;
      jointdef.upperTranslation = 0.0F;
      world.createJoint(jointdef);
   }

   public WheelImpl(
           @NotNull World world,
           @NotNull Car car,
           float posX,
           float posY,
           float width,
           float height
   ) {
      super(world, car, posX, posY, width, height, false);
   }
}
