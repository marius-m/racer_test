package lt.markmerkk.app.network

import com.esotericsoftware.kryonet.EndPoint
import lt.markmerkk.app.network.events.EventHello
import lt.markmerkk.app.network.events.EventPlayerMovement
import lt.markmerkk.app.network.events.EventPlayersRegister
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.models.PlayerRegister
import lt.markmerkk.app.network.events.models.ReportPlayer
import java.util.*

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
object Network {
    fun register(endPoint: EndPoint) {
        val kryo = endPoint.kryo
        kryo.register(EventHello::class.java)
        kryo.register(EventPlayersUpdate::class.java)
        kryo.register(ArrayList::class.java)
        kryo.register(ReportPlayer::class.java)

        // Registering player
        kryo.register(EventPlayersRegister::class.java)
        kryo.register(PlayerRegister::class.java)
        kryo.register(EventPlayerMovement::class.java)
    }
}