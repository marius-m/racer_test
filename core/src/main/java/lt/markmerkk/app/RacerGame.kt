package lt.markmerkk.app

import com.badlogic.gdx.Game
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class RacerGame(
        private val isHost: Boolean
) : Game() {

    override fun create() {
        val gameScreen = GameScreen(isHost)
        gameScreen.create()
        screen = gameScreen
    }
}