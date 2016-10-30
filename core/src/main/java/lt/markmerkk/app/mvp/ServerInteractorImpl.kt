package lt.markmerkk.app.mvp

import com.esotericsoftware.kryonet.Connection
import lt.markmerkk.app.Const
import lt.markmerkk.app.network.GameServer
import com.esotericsoftware.kryonet.Listener
import lt.markmerkk.app.network.Network
import lt.markmerkk.app.network.events.NetworkEvent

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerInteractorImpl : ServerInteractor {

    override var eventProvider: NetworkEventProvider? = null
    override var server: GameServer? = null

    override fun start() {
        server = GameServer()
        Network.register(server!!)
        server!!.bind(Const.PORT_TCP, Const.PORT_UDP)
        server!!.start()
        server!!.addListener(object : Listener() {
            override fun received(connection: Connection, eventObject: Any) {
                super.received(connection, eventObject)
                if (eventObject !is NetworkEvent) return
                eventProvider?.event(eventObject)
            }
        })
    }

    override fun stop() {
        server?.stop()
    }

}