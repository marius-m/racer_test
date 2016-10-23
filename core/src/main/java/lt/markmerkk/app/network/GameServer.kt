package lt.markmerkk.app.network

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Server
import com.esotericsoftware.minlog.Log
import lt.markmerkk.app.Const

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class GameServer : Server() {

    val serverLogger = GameLogger()

    init {
        Log.set(Const.LOG_LEVEL)
        Log.setLogger(serverLogger)
    }

    override fun newConnection(): Connection {
        return GameConnection()
    }

}