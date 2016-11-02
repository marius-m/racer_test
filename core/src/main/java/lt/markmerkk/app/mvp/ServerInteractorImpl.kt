package lt.markmerkk.app.mvp

import com.esotericsoftware.kryonet.Connection
import lt.markmerkk.app.Const
import lt.markmerkk.app.network.GameServer
import com.esotericsoftware.kryonet.Listener
import lt.markmerkk.app.network.Network
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.NetworkEvent
import lt.markmerkk.app.network.events.models.ReportPlayer
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerInteractorImpl : ServerInteractor {

    lateinit private var eventProvider: NetworkEventProvider
    lateinit var server: GameServer

    override fun start(eventProvider: NetworkEventProvider) {
        this.eventProvider = eventProvider
        server = GameServer()
        Network.register(server)
        server.bind(Const.PORT_TCP, Const.PORT_UDP)
        server.start()
        server.addListener(object : Listener() {
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
        })
    }

    override fun stop() {
        server.stop()
    }

    override fun sendPlayerUpdate(reportPlayers: List<ReportPlayer>) {
        val playersUpdate = EventPlayersUpdate()
        playersUpdate.reportPlayers = reportPlayers
        server.sendToAllTCP(playersUpdate)
    }

    companion object {
        val logger = LoggerFactory.getLogger(ServerInteractorImpl::class.java)!!
    }

}