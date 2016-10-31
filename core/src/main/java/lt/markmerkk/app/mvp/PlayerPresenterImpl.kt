package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.CarImpl
import lt.markmerkk.app.entities.PlayerServerImpl
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractor
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractorImpl
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerPresenterImpl(
        private val world: World,
        private val spriteBundleInteractors: MutableList<SpriteBundleInteractor>
) : PlayerPresenter {

    private val players: MutableList<PlayerServerImpl> = mutableListOf()

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

    override fun createPlayer(): PlayerServerImpl {
        val carSprite = Sprite(Texture(Gdx.files.internal("data/car_small.png")))
        val player = PlayerServerImpl(
                "test_player",
                CarImpl(world, Vector2(2.0f, 5.0f)),
                carSprite
        )
        return player
    }

    override fun addPlayer(player: PlayerServerImpl) {
        spriteBundleInteractors.add(SpriteBundleInteractorImpl(player.carSprite))
        players.add(player)
    }

    override fun removePlayer(player: PlayerServerImpl) {
        players.remove(player)
        // todo : missing sprite bundle remover
    }

}