package lt.markmerkk.app.entities

import com.badlogic.gdx.graphics.g2d.Sprite

class PlayerClientImpl(
        override val id: Int = -1,
        override val name: String
) : PlayerClient {

    lateinit var sprite: Sprite

    override fun update(
            positionX: Float,
            positionY: Float,
            angle: Float
    ) {
        sprite.setPosition(positionX, positionY)
        sprite.rotation = angle
    }

    override fun destroy() {

    }
}