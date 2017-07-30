package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.PlayerServer

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
interface PlayerPresenterServer {
    fun render(deltaTime: Float)

    /**
     * Creates a new player with a bound connection id
     */
    fun createPlayerById(connectionId: Int)

    /**
     * Tries to remove a player by connection id
     */
    fun removePlayerByConnectionId(connectionId: Int)

    /**
     * Returns all players
     */
    fun players(): List<PlayerServer>
}