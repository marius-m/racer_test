package lt.markmerkk.app.box2d.temp_components.wheel;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import lt.markmerkk.app.box2d.Car;
import org.jetbrains.annotations.NotNull;

public final class RevolvingWheelImpl extends BaseWheelImpl {
   public void initJoint(
           @NotNull World world,
           @NotNull Car car
   ) {
      RevoluteJointDef jointdef = new RevoluteJointDef();
      jointdef.initialize(
              car.getBody(),
              this.getBody(),
              this.getBody().getWorldCenter()
      );
      jointdef.enableMotor = false;
      world.createJoint(jointdef);
   }

   public RevolvingWheelImpl(
           @NotNull World world,
           @NotNull Car car,
           float posX,
           float posY,
           float width,
           float height
   ) {
      super(world, car, posX, posY, width, height, true);
   }
}
