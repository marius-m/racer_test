package lt.markmerkk.app.network

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Server
import com.esotericsoftware.minlog.Log
import lt.markmerkk.app.Const
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class GameServer : Server() {

    val serverLogger = object : Log.Logger() {
        override fun print(p0: String?) {
            logger.debug(p0)
        }
    }

    init {
//        Log.set(Const.LOG_LEVEL)
//        Log.setLogger(serverLogger)
    }

    override fun newConnection(): Connection {
        return GameConnection()
    }

    private val logger = LoggerFactory.getLogger(GameServer::class.java)!!

}