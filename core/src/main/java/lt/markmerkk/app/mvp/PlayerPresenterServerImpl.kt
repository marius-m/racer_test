package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.PlayerServer
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerPresenterServerImpl(
        private val players: MutableList<PlayerServer>
) : PlayerPresenter<PlayerServer> {

    //region Life-cycle

    override fun onAttach() {}

    override fun onDetach() {}

    override fun render(deltaTime: Float) {
        players.forEach {
            it.update(deltaTime)
        }
    }

    override fun addPlayer(player: PlayerServer) {
        players.add(player)
    }

    override fun removePlayer(player: PlayerServer) {
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