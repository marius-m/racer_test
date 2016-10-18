package lt.markmerkk.app.box2d.temp_components.wheel;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import lt.markmerkk.app.box2d.Car;
import org.jetbrains.annotations.NotNull;

public interface Wheel {
   @NotNull
   World getWorld();

   @NotNull
   Body getBody();

   @NotNull
   Car getCar();

   float getPosX();

   float getPosY();

   float getWidth();

   float getHeight();

   @NotNull
   Vector2 directionVector();

   @NotNull
   Vector2 localVelocity();

   void changeAngle(float var1);

   void killSidewayVector();
}
