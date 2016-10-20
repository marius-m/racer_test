package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.physics.box2d.World

/**
 * @author mariusmerkevicius
 * @since 2016-10-20
 */
class WorldPresenterImpl(
        private val world: World
) : WorldPresenter {

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render() {
        world.step(Gdx.app.graphics.deltaTime, 3, 3)
        world.clearForces()
    }
}