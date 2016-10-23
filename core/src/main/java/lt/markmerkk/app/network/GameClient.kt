package lt.markmerkk.app.network

import com.esotericsoftware.kryonet.Client
import com.esotericsoftware.minlog.Log

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class GameClient : Client() {

    val serverLogger = GameLogger()

    init {
        Log.set(Log.LEVEL_TRACE)
        Log.setLogger(serverLogger)
    }

}