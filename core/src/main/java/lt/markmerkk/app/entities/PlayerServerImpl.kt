package lt.markmerkk.app.entities

import com.badlogic.gdx.graphics.g2d.Sprite
import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerServerImpl(
        override val id: Int = -1,
        override val name: String,
        override val car: Car,
        override val carSprite: Sprite
) : Player {
    override fun update(deltaTime: Float) {
        car.update(deltaTime)
        carSprite.setPosition(
                GameScreen.PIXELS_PER_METER * car.body.position.x - carSprite.width / 2,
                GameScreen.PIXELS_PER_METER * car.body.position.y - carSprite.height / 2
        )
        carSprite.rotation = Math.toDegrees(car.body.angle.toDouble()).toFloat()
    }
}