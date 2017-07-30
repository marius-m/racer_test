package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
interface PlayerPresenter<in Player> : Presenter {
    fun render(deltaTime: Float)

    fun addPlayer(player: Player)
    fun removePlayer(player: Player)
    fun removePlayerByConnectionId(connectionId: Int)
}