package lt.markmerkk.app.mvp

/**
 * Creates provides a new instance of a player
 */
interface PlayerProvider<out Player> {
    /**
     * Creates a new player with a provided connection id
     */
    fun create(
            connectionId: Int
    ): Player

    fun all(): List<Player>
}