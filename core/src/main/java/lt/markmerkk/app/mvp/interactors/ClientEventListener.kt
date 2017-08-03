package lt.markmerkk.app.mvp.interactors

import lt.markmerkk.app.network.events.models.PlayerPosition
import lt.markmerkk.app.network.events.models.PlayerRegister
import lt.markmerkk.app.network.events.models.ReportPlayer

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
interface ClientEventListener {
    fun onConnected(connectionId: Int)
    fun onDisconnected(connectionId: Int)

    /**
     * Reports all registered player
     */
    fun onPlayersRegister(registeredPlayers: List<PlayerRegister>)

    /**
     * Reports all player positions in the map
     */
    fun onPlayersPosition(playersPosition: List<PlayerPosition>)
}