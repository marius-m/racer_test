package lt.markmerkk.app.entities

import com.badlogic.gdx.graphics.g2d.Sprite
import lt.markmerkk.app.box2d.CarBridge
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerServerImpl(
        override val id: Int = -1,
        override val name: String,
        override val carBridge: CarBridge,
        override val carSprite: Sprite
) : Player {
    override fun update(deltaTime: Float) {
        carBridge.update(deltaTime)
        carSprite.setPosition(
                GameScreen.PIXELS_PER_METER * carBridge.x - carSprite.width / 2,
                GameScreen.PIXELS_PER_METER * carBridge.y - carSprite.height / 2
        )
        carSprite.rotation = Math.toDegrees(carBridge.angle.toDouble()).toFloat()
    }
}