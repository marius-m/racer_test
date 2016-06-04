package lt.markmerkk.app.box2d.temp_components

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.BoxProperty

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class PenComponent(
        val world: World,
        val worldWidth: Float,
        val worldHeight: Float
) {
    init {
        val center = Vector2(worldWidth / 2, worldHeight / 2)
        val pen1 = BoxProperty(world, 1f, 6f, Vector2(center.x - 3, center.y))
        val pen2 = BoxProperty(world, 1f, 6f, Vector2(center.x + 3, center.y))
        val pen3 = BoxProperty(world, 5f, 1f, Vector2(center.x, center.y + 2.5f))
    }
}