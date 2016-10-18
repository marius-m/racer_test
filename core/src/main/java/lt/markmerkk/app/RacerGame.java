package lt.markmerkk.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import lt.markmerkk.app.screens.GameScreen;

public final class RacerGame extends Game {
   public void create() {
      GameScreen gameScreen = new GameScreen();
      gameScreen.create();
      this.screen = (Screen)gameScreen;
   }
}
