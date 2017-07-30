package lt.markmerkk.app.entities

import com.badlogic.gdx.graphics.g2d.Sprite

/**
 * Represents player on client side (drawing of sprites)
 */
interface PlayerClient {
    val id: Int
    val name: String

    fun update(
            positionX: Float,
            positionY: Float,
            angle: Float
    )

    fun destroy()
}