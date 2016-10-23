package lt.markmerkk.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.esotericsoftware.minlog.Log
import lt.markmerkk.app.RacerGame
import lt.markmerkk.app.network.GameLogger

object DesktopLauncher {
    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        LwjglApplication(RacerGame(), config)
   }
}
