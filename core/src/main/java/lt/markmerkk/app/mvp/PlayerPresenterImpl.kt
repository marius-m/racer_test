package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.CarBridgeEmptyImpl
import lt.markmerkk.app.box2d.CarBridgeImpl
import lt.markmerkk.app.box2d.CarImpl
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.entities.PlayerServerImpl
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerPresenterImpl(
        private val isHost: Boolean,
        private val world: World,
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

    //endregion

    override fun createPlayer(connectionId: Int): Player {
        logger.debug("Creating a new player with $connectionId id")
        val carSprite = Sprite(Texture(Gdx.files.internal("data/car_small.png")))
        val carBridge = if (isHost) {
            CarBridgeImpl(CarImpl(world, Vector2(2.0f, 5.0f)))
        } else {
            CarBridgeEmptyImpl()
        }
        val player = PlayerServerImpl(
                id = connectionId,
                name = "test_player_"+players.size,
                carBridge = carBridge,
                carSprite = carSprite
        )
        return player
    }

    override fun addPlayer(player: Player) {
        logger.debug("Adding $player")
        players.add(player)
    }

    override fun removePlayer(player: Player) {
        logger.debug("Removing $player")
        player.carBridge.destroy()
        players.remove(player)
    }

    override fun removePlayerByConnectionId(connectionId: Int) {
        val player = players.find { it.id == connectionId } ?: return
        removePlayer(player)
    }

    companion object {
        val logger = LoggerFactory.getLogger(PlayerPresenterImpl::class.java)!!
    }

}