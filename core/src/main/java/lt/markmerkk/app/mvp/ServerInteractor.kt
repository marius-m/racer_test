package lt.markmerkk.app.mvp

import lt.markmerkk.app.network.GameServer
import lt.markmerkk.app.network.events.ReportPlayer

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
interface ServerInteractor {
    var server: GameServer?
    var eventProvider: NetworkEventProvider?

    fun start()
    fun stop()
    fun sendPlayerUpdate(reportPlayers: List<ReportPlayer>)
}