package lt.markmerkk.app.entities

import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.screens.GameScreen
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerServerImpl(
        override val id: Int = -1,
        override val name: String,
        private val car: Car
) : PlayerServer {

    override fun updateMovement(movement: Movement) {
        // Acceleration
        when (movement) {
            Movement.FORWARD_START,
            Movement.FORWARD_STOP,
            Movement.BACKWARD_START,
            Movement.BACKWARD_STOP -> {
                when (movement) {
                    Movement.FORWARD_START -> car.accForward()
                    Movement.BACKWARD_START -> car.accBackward()
                    else -> car.accStop()
                }
            }
            Movement.LEFT_START,
            Movement.LEFT_STOP,
            Movement.RIGHT_START,
            Movement.RIGHT_STOP -> {
                when (movement) {
                    Movement.LEFT_START -> car.steerLeft()
                    Movement.RIGHT_START -> car.steerRight()
                    else -> car.steerNone()
                }
            }
            else -> {
                logger.debug("[ERROR] Car unidentified car movement!")
                car.accStop()
                car.steerNone()
            }
        }
    }

    override fun update(deltaTime: Float) {
        car.update(deltaTime)
//        val newPositionX = GameScreen.PIXELS_PER_METER * car.x - carSprite.width / 2
//        val newPositionY = GameScreen.PIXELS_PER_METER * car.y - carSprite.height / 2
//        val newAngle = Math.toDegrees(car.angle.toDouble()).toFloat()

//        if (newPositionX != carSprite.x ||
//                newPositionY != carSprite.y ||
//                carSprite.rotation != newAngle) {
//        }
//
//        carSprite.setPosition(newPositionX, newPositionY)
//        carSprite.rotation = newAngle
    }

    override fun getPositionX(): Float {
        return GameScreen.PIXELS_PER_METER * car.positionX()
    }

    override fun getPositionY(): Float {
        return GameScreen.PIXELS_PER_METER * car.positionY()
    }

    override fun getAngle(): Float {
        return Math.toDegrees(car.angle().toDouble()).toFloat()
    }

    override fun destroy() {
        car.destroy()
    }

    companion object {
        val logger = LoggerFactory.getLogger(PlayerServerImpl::class.java)
    }
}