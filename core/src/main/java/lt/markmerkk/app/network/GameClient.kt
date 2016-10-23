package lt.markmerkk.app.network

import com.esotericsoftware.kryonet.Client
import com.esotericsoftware.minlog.Log
import lt.markmerkk.app.Const

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class GameClient : Client() {

    val serverLogger = GameLogger()

    init {
        Log.set(Const.LOG_LEVEL)
        Log.setLogger(serverLogger)
    }

}