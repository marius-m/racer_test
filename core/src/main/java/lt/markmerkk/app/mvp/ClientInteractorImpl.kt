package lt.markmerkk.app.mvp

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import lt.markmerkk.app.Const
import lt.markmerkk.app.entities.Movement
import lt.markmerkk.app.network.GameClient
import lt.markmerkk.app.network.Network
import lt.markmerkk.app.network.events.EventPlayerMovement
import lt.markmerkk.app.network.events.NetworkEvent
import org.slf4j.LoggerFactory
import java.net.InetAddress

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientInteractorImpl : ClientInteractor {

    lateinit private var eventProvider: NetworkEventProvider
    private val client = GameClient()

    override fun start(eventProvider: NetworkEventProvider) {
        this.eventProvider = eventProvider
        client.start()
        Network.register(client)
        client.addListener(listener)
        client.connect(
                Const.CONN_TIMEOUT_MILLIS,
                InetAddress.getByName("127.0.0.1"),
                Const.PORT_TCP,
                Const.PORT_UDP
        )
    }

    override fun stop() {
        client.removeListener(listener)
        client.stop()
    }

    //region Listeners

    private val listener: Listener = object : Listener() {

        override fun connected(connection: Connection) {
            super.connected(connection)
            eventProvider.connected(connection.id)
        }

        override fun disconnected(connection: Connection) {
            super.disconnected(connection)
            eventProvider.disconnected(connection.id)
        }

        override fun received(connection: Connection, eventObject: Any) {
            super.received(connection, eventObject)
            if (eventObject !is NetworkEvent) return
            eventProvider.event(eventObject)
        }
    }

    //endregion

    //region Sent Events

    override fun sendMovementEventCode(connectionId: Int, movementEvent: Movement) {
        client.sendTCP(EventPlayerMovement(connectionId, movementEvent.toCode()))
    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ClientInteractorImpl::class.java)!!
    }

}