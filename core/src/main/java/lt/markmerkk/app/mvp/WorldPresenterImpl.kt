package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractor
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-20
 */
class WorldPresenterImpl(
        private val world: World,
        private val spriteBundleInteractors: List<SpriteBundleInteractor>
) : WorldPresenter {

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render(deltaTime: Float) {
        world.step(Gdx.app.graphics.deltaTime, 3, 3)
        world.clearForces()
        spriteBundleInteractors.forEach {
            it.updatePosition(
                    GameScreen.PIXELS_PER_METER * it.body.position.x - it.sprite.width / 2,
                    GameScreen.PIXELS_PER_METER * it.body.position.y - it.sprite.height / 2
            )
            it.updateAngle(Math.toDegrees(it.body.angle.toDouble()).toFloat())
        }
    }
}