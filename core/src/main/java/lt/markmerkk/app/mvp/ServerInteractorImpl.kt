package lt.markmerkk.app.mvp

import com.esotericsoftware.kryonet.Connection
import lt.markmerkk.app.Const
import lt.markmerkk.app.network.GameServer
import com.esotericsoftware.kryonet.Listener
import lt.markmerkk.app.network.Network
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.NetworkEvent
import lt.markmerkk.app.network.events.ReportPlayer

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
            override fun connected(connection: Connection) {
                super.connected(connection)
                eventProvider?.connected(connection.id)
            }

            override fun disconnected(connection: Connection) {
                super.disconnected(connection)
                eventProvider?.disconnected(connection.id)
            }

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

    override fun sendPlayerUpdate(reportPlayers: List<ReportPlayer>) {
        val playersUpdate = EventPlayersUpdate()
        playersUpdate.reportPlayers = reportPlayers
        server?.sendToAllTCP(playersUpdate)
    }

}