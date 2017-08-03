package lt.markmerkk.app.mvp

import lt.markmerkk.app.network.events.models.PlayerRegister
import lt.markmerkk.app.network.events.models.ReportPlayer

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
interface ServerInteractor {
    fun start(eventProvider: NetworkEventProvider)
    fun stop()
    fun sendPlayerRegister(registeredPlayers: List<PlayerRegister>)
}