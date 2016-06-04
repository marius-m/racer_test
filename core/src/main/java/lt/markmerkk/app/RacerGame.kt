package lt.markmerkk.app

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class RacerGame : Game() {

    override fun create() {
        val gameScreen = GameScreen()
        gameScreen.create()
        screen = gameScreen
    }
}