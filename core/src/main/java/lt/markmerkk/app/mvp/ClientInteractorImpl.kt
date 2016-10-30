package lt.markmerkk.app.mvp

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import lt.markmerkk.app.Const
import lt.markmerkk.app.network.GameClient
import lt.markmerkk.app.network.Network
import lt.markmerkk.app.network.events.EventRegister
import java.net.InetAddress

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientInteractorImpl : ClientInteractor {

    private val client = GameClient()

    override fun start() {
        client.start()
        Network.register(client)
        client.addListener(object : Listener() {
            override fun connected(connection: Connection) {
                super.connected(connection)
                val registerEvent = EventRegister()
                registerEvent.name = connection.remoteAddressTCP.toString()
                client.sendTCP(registerEvent)
            }
        })
        client.connect(
                Const.CONN_TIMEOUT_MILLIS,
                InetAddress.getByName("127.0.0.1"),
                Const.PORT_TCP,
                Const.PORT_UDP
        )
    }

    override fun stop() {
        client.stop()
    }
}