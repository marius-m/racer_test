package lt.markmerkk.app.entities

import com.badlogic.gdx.graphics.g2d.Sprite
import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
interface Player {
    val id: Int
    val name: String
    val car: Car
    val carSprite: Sprite

    fun update(deltaTime: Float)

}