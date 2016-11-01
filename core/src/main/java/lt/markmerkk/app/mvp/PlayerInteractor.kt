package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Player

/**
 * @author mariusmerkevicius
 * @since 2016-11-01
 */
interface PlayerInteractor {
    fun createPlayer(connectionId: Int): Player
    fun addPlayer(player: Player)
    fun removePlayer(player: Player)
    fun removePlayerByConnectionId(connectionId: Int)
}