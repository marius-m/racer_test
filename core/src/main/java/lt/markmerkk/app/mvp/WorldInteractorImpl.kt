package lt.markmerkk.app.mvp

import com.badlogic.gdx.physics.box2d.World

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class WorldInteractorImpl(
        private val world: World
) : WorldInteractor {
    override fun step(deltaTime: Float) {
        world.step(deltaTime, 3, 3)
        world.clearForces()
    }
}