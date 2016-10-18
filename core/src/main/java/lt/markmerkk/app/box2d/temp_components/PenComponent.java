package lt.markmerkk.app.box2d.temp_components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import lt.markmerkk.app.box2d.BoxProperty;
import org.jetbrains.annotations.NotNull;

public final class PenComponent {
   @NotNull
   private final World world;
   private final float worldWidth;
   private final float worldHeight;

   public PenComponent(
           @NotNull World world,
           float worldWidth,
           float worldHeight
   ) {
      super();
      this.world = world;
      this.worldWidth = worldWidth;
      this.worldHeight = worldHeight;

      Vector2 center = new Vector2(this.worldWidth / (float)2, this.worldHeight / (float)2);

      new BoxProperty(this.world, 1.0F, 6.0F, new Vector2(center.x - (float)3, center.y));
      new BoxProperty(this.world, 1.0F, 6.0F, new Vector2(center.x + (float)3, center.y));
      new BoxProperty(this.world, 5.0F, 1.0F, new Vector2(center.x, center.y + 2.5F));
   }
}
