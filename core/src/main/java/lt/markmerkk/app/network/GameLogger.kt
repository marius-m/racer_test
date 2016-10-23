package lt.markmerkk.app.network

import com.esotericsoftware.minlog.Log
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class GameLogger : Log.Logger() {
    private val logger = LoggerFactory.getLogger(GameLogger::class.java)!!

    override fun print(p0: String?) {
        logger.debug(p0)
    }
}