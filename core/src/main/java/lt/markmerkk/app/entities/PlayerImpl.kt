package lt.markmerkk.app.entities

import com.badlogic.gdx.graphics.g2d.Sprite
import lt.markmerkk.app.box2d.CarBridge
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerImpl(
        override val id: Int = -1,
        override val name: String,
        override val carBridge: CarBridge,
        override val carSprite: Sprite
) : Player {

    override var dirty: Boolean = false

    override fun update(deltaTime: Float) {
        carBridge.update(deltaTime)
        val newPositionX = GameScreen.PIXELS_PER_METER * carBridge.x - carSprite.width / 2
        val newPositionY = GameScreen.PIXELS_PER_METER * carBridge.y - carSprite.height / 2
        val newAngle = Math.toDegrees(carBridge.angle.toDouble()).toFloat()

        if (newPositionX != carSprite.x ||
                newPositionY != carSprite.y ||
                carSprite.rotation != newAngle) {
            dirty = true
        }

        carSprite.setPosition(newPositionX, newPositionY)
        carSprite.rotation = newAngle
    }
}