package lt.markmerkk.app.mvp

import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.entities.Player
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerPresenterImpl(
        private val interactor: PlayerInteractor,
        private val players: MutableList<Player>
) : PlayerPresenter {

    //region Life-cycle

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render(deltaTime: Float) {
        players.forEach {
            it.update(deltaTime)
        }
    }

    override fun createPlayer(connectionId: Int): Player {
        return interactor.createPlayer(connectionId)
    }

    override fun addPlayer(player: Player) {
        interactor.addPlayer(player)
    }

    override fun removePlayer(player: Player) {
        interactor.removePlayer(player)
    }

    override fun removePlayerByConnectionId(connectionId: Int) {
        interactor.removePlayerByConnectionId(connectionId)
    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(PlayerPresenterImpl::class.java)!!
    }

}