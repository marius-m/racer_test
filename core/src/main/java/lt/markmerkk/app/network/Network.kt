package lt.markmerkk.app.network

import com.esotericsoftware.kryonet.EndPoint
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.ReportPlayer
import java.util.*

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
object Network {
    fun register(endPoint: EndPoint) {
        val kryo = endPoint.kryo
        kryo.register(EventPlayersUpdate::class.java)
        kryo.register(ArrayList::class.java)
        kryo.register(ReportPlayer::class.java)
    }
}