package lt.markmerkk.app

import com.esotericsoftware.minlog.Log

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
object Const {
    const val LOG_LEVEL = Log.LEVEL_DEBUG
    const val CONN_TIMEOUT_MILLIS: Int = 5000
    const val PORT_TCP: Int = 3000
    const val PORT_UDP: Int = 3001
    const val NO_CONNECTION_ID = -1
}