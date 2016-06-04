package lt.markmerkk.app.box2d.temp_components

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.BoxProperty

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class WallComponent(
        val world: World,
        val worldWidth: Float,
        val worldHeight: Float
) {
    init {
        val wallBottom = BoxProperty(
                world = world,
                width = worldWidth,
                height = 1f,
                position = Vector2(worldWidth / 2, 0.5f)
        )
        val wallLeft = BoxProperty(
                world = world,
                width = 1f,
                height = worldHeight - 2,
                position = Vector2(0.5f, worldHeight / 2)
        )
        val wallTop = BoxProperty(
                world = world,
                width = worldWidth,
                height = 1f,
                position = Vector2(worldWidth / 2, worldHeight - 0.5f)
        )
        val wallRight = BoxProperty(
                world = world,
                width = 1f,
                height = worldHeight - 2,
                position = Vector2(worldWidth - 0.5f, worldHeight / 2)
        )

    }
}