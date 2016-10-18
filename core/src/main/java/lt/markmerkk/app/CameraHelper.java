package lt.markmerkk.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import org.jetbrains.annotations.NotNull;

public final class CameraHelper {
   private int viewportWidth;
   private int viewportHeight;
   private final int screenWidth;
   private final int screenHeight;
   private final float aspect;
   private final OrthographicCamera camera;
   private final int virtualWidth;
   private final int virtualHeight;

   public final int getViewportWidth() {
      return this.viewportWidth;
   }

   public final int getViewportHeight() {
      return this.viewportHeight;
   }

   @NotNull
   public final Matrix4 getCombine() {
      return camera.combined;
   }

   public CameraHelper(int virtualWidth, int virtualHeight) {
      this.virtualWidth = virtualWidth;
      this.virtualHeight = virtualHeight;
      this.camera = new OrthographicCamera();
      this.screenWidth = Gdx.graphics.getWidth();
      this.screenHeight = Gdx.graphics.getHeight();
      this.aspect = (float)(this.virtualWidth / this.virtualHeight);
      if((float)(this.screenWidth / this.screenHeight) >= this.aspect) {
         this.viewportHeight = this.virtualHeight;
         this.viewportWidth = this.viewportHeight * this.screenWidth / this.screenHeight;
      } else {
         this.viewportWidth = this.virtualWidth;
         this.viewportHeight = this.viewportWidth * this.screenHeight / this.screenWidth;
      }

      this.camera.setToOrtho(false, (float)this.viewportWidth, (float)this.viewportHeight);
   }
}
