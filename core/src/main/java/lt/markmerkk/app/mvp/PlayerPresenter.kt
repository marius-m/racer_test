package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Player

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
interface PlayerPresenter : Presenter {
    fun render(deltaTime: Float)

    fun addPlayer(player: Player)
    fun removePlayer(player: Player)
    fun removePlayerByConnectionId(connectionId: Int)
}