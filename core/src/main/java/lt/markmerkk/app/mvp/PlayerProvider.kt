package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Player

/**
 * Creates provides a new instance of a player
 */
interface PlayerProvider {
    /**
     * Creates a new player with a provided connection id
     */
    fun create(
            connectionId: Int
    ): Player
}