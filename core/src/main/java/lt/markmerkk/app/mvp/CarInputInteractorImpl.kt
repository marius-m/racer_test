package lt.markmerkk.app.mvp

import lt.markmerkk.app.box2d.CarBox2D

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
class CarInputInteractorImpl(
        private val carBox2D: CarBox2D
) : CarInputInteractor {
    override fun moveForward() {
        carBox2D.accelerate = CarBox2D.ACC_FORWARD
    }

    override fun moveBackward() {
        carBox2D.accelerate = CarBox2D.ACC_BACKWARD
    }

    override fun moveNone() {
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
}