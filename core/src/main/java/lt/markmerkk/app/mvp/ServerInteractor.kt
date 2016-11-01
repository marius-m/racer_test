package lt.markmerkk.app.mvp

import lt.markmerkk.app.network.events.ReportPlayer

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
interface ServerInteractor {
    fun start(eventProvider: NetworkEventProvider)
    fun stop()
    fun sendPlayerUpdate(reportPlayers: List<ReportPlayer>)
}