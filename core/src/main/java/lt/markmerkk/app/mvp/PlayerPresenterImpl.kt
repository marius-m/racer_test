package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.CarImpl
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.entities.PlayerServerImpl

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerPresenterImpl(
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

    override fun createPlayer(connectionId: Int): PlayerServerImpl {
        val carSprite = Sprite(Texture(Gdx.files.internal("data/car_small.png")))
        val player = PlayerServerImpl(
                id = connectionId,
                name = "test_player_"+players.size,
                car = CarImpl(world, Vector2(2.0f, 5.0f)),
                carSprite = carSprite
        )
        return player
    }

    override fun addPlayer(player: PlayerServerImpl) {
        players.add(player)
    }

    override fun removePlayer(player: PlayerServerImpl) {
        players.remove(player)
    }

    override fun removePlayer(connectionId: Int) {
        val player = players.find { it.id == connectionId } ?: return
        player.car.destroy()
        players.remove(player)
    }

}