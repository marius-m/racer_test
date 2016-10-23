package lt.markmerkk.app.mvp

import com.esotericsoftware.kryonet.Connection
import lt.markmerkk.app.Const
import lt.markmerkk.app.network.GameServer
import com.esotericsoftware.kryonet.Listener
import lt.markmerkk.app.network.events.NetworkEvent

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerInteractorImpl : ServerInteractor {

    override var eventProvider: ServerEventProvider? = null
    private var server: GameServer? = null

    override fun start() {
        server = GameServer().apply {
            bind(Const.PORT_TCP, Const.PORT_UDP)
            start()
        }
        server?.addListener(object : Listener() {
            override fun received(connection: Connection, eventObject: Any) {
                super.received(connection, eventObject)
                eventProvider?.event(eventObject as NetworkEvent)
            }
        })
    }

    override fun stop() {
        server?.stop()
    }

}