package lt.markmerkk.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import lt.markmerkk.app.CameraHelper;
import lt.markmerkk.app.box2d.Car;
import lt.markmerkk.app.box2d.temp_components.PenComponent;
import lt.markmerkk.app.box2d.temp_components.WallComponent;
import lt.markmerkk.app.network.RaceServer;
import org.java_websocket.WebSocketImpl;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public final class GameScreen implements Screen {
   private float worldWidth;
   private float worldHeight;
   @NotNull
   public SpriteBatch spriteBatch;
   @NotNull
   public CameraHelper camera;
   @NotNull
   public World world;
   @NotNull
   public Matrix4 debugMatrix;
   @NotNull
   public Box2DDebugRenderer debugRenderer;
   @NotNull
   public Car car;
   public static final int VIRTUAL_WIDTH = 480;
   public static final int VIRTUAL_HEIGHT = 320;
   public static final int PIXELS_PER_METER = 16;

   private RaceServer server;
   private BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );

   public GameScreen() {
      WebSocketImpl.DEBUG = true;
      int port = 8887; // 843 flash policy port
      try {
         server = new RaceServer(port);
         server.start();
         System.out.println( "ChatServer started on port: " + server.getPort() );
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }
   }

   public final void create() {
      this.world = new World(new Vector2(0.0F, 0.0F), true);
      this.camera = new CameraHelper(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
      this.car = new Car(world, new Vector2(20.0F, 10.0F));

      float spriteCenterWidth = car.getSprite().getWidth() / (float) 2;
      float spriteCenterHeight = car.getSprite().getHeight() / (float) 2;

      car.getSprite().setOrigin(spriteCenterWidth, spriteCenterHeight);

      this.worldWidth = (float)camera.getViewportWidth() / (float)PIXELS_PER_METER;
      this.worldHeight = (float)camera.getViewportHeight() / (float)PIXELS_PER_METER;

      this.spriteBatch = new SpriteBatch();
      this.debugRenderer = new Box2DDebugRenderer();
      this.spriteBatch.setProjectionMatrix(camera.getCombine());
      this.debugMatrix = camera.getCombine();

      this.debugMatrix.scale((float)PIXELS_PER_METER, (float)PIXELS_PER_METER, (float)PIXELS_PER_METER);

      new PenComponent(world, worldWidth, worldHeight);
      new WallComponent(world, worldWidth, worldHeight);
   }

   public void pause() {
   }

   public void resume() {
   }

   public void show() {
   }

   public void render(float delta) {
//      handleServer();
      Gdx.gl.glClearColor(0.0F, 0.0F, 0.2F, 1.0F);
      Gdx.gl.glClear(16384);

      world.step(Gdx.app.getGraphics().getDeltaTime(), 3, 3);
      world.clearForces();
      Box2DDebugRenderer debugRenderer = this.debugRenderer;
      debugRenderer.render(world, debugMatrix);
      car.getSprite().setPosition(
              PIXELS_PER_METER * car.getBody().getPosition().x - car.getSprite().getWidth() / 2,
              PIXELS_PER_METER * car.getBody().getPosition().y - car.getSprite().getHeight() / 2
      );
      car.getSprite().setRotation((float)Math.toDegrees((double)car.getBody().getAngle()));
      spriteBatch.begin();
      car.getSprite().draw(spriteBatch);
      spriteBatch.end();

      if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
         car.setSteer(Car.STEER_LEFT);
      } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
         car.setSteer(Car.STEER_RIGHT);
      } else {
         car.setSteer(Car.STEER_NONE);
      }

      if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
         car.setAccelerate(Car.ACC_FORWARD);
      } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
         car.setAccelerate(Car.ACC_BACKWARD);
      } else {
         car.setAccelerate(Car.ACC_NONE);
      }

      world.step(Gdx.app.getGraphics().getDeltaTime(), 3, 3);
      world.clearForces();
      car.update(Gdx.app.getGraphics().getDeltaTime());
      handleServer();
   }

   private void handleServer() {
      if (server == null) return;
      try {
         server.sendToAll(
                 "CarPos: " +
                         car.getSprite().getX() +
                         "x" +
                         car.getSprite().getY()
         );
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void resize(int width, int height) {
   }

   public void hide() {
   }

   public void dispose() {
      spriteBatch.dispose();
      car.getTexture().dispose();
      try {
         if (server != null) {
            server.stop();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

}
