package lt.markmerkk.app.box2d

/**
 * @author mariusmerkevicius
 * @since 2016-11-01
 */
class CarImpl(
        private val carBox2D: CarBox2D
) : Car {

    override fun accForward() {
        carBox2D.accelerate = CarBox2D.ACC_FORWARD
    }

    override fun accBackward() {
        carBox2D.accelerate = CarBox2D.ACC_BACKWARD
    }

    override fun accStop() {
        carBox2D.accelerate = CarBox2D.ACC_NONE
    }

    override fun steerLeft() {
        carBox2D.steer = CarBox2D.STEER_LEFT
    }

    override fun steerRight() {
        carBox2D.steer = CarBox2D.STEER_RIGHT
    }

    override fun steerNone() {
        carBox2D.steer = CarBox2D.STEER_NONE
    }

    override fun update(deltaTime: Float) {
        carBox2D.update(deltaTime)
    }

    override fun positionX(): Float {
        return carBox2D.body.position.x
    }

    override fun positionY(): Float {
        return carBox2D.body.position.y
    }

    override fun angle(): Float {
        return carBox2D.body.angle
    }

    override fun destroy() {
        carBox2D.destroy()
    }
}