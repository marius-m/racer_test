package lt.markmerkk.app.box2d

import com.badlogic.gdx.physics.box2d.World

/**
 * @author mariusmerkevicius
 * @since 2017-08-03
 */
class WorldProviderImpl(
        private val world: World
) : WorldProvider {
    override fun get(): World = world
}