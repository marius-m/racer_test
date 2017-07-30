package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.PlayerClient
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerPresenterClientImpl(
        private val players: MutableList<PlayerClient>
) : PlayerPresenter<PlayerClient> {

    //region Life-cycle

    override fun onAttach() {}

    override fun onDetach() {}

    override fun render(deltaTime: Float) {}

    override fun addPlayer(player: PlayerClient) {
        players.add(player)
    }

    override fun removePlayer(player: PlayerClient) {
        players.remove(
                player.apply {
                    destroy()
                }
        )
    }

    override fun removePlayerByConnectionId(connectionId: Int) {
        players.find { it.id == connectionId }
                .let { removePlayer(it!!) }
    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(PlayerPresenterServerImpl::class.java)!!
    }

}