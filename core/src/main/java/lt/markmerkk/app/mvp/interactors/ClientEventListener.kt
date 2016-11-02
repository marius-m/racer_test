package lt.markmerkk.app.mvp.interactors

import lt.markmerkk.app.network.events.models.ReportPlayer

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
interface ClientEventListener {
    fun onPlayersUpdate(reportPlayers: List<ReportPlayer>)
    fun onConnected(connectionId: Int)
    fun onDisconnected(connectionId: Int)
}