package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.entities.PlayerServerImpl

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
interface PlayerPresenter : Presenter {
    fun render(deltaTime: Float)

    /**
     * Creates a new player
     */
    fun createPlayer(connectionId: Int): Player
    fun addPlayer(player: Player)
    fun removePlayer(player: Player)
    fun removePlayerByConnectionId(connectionId: Int)
}