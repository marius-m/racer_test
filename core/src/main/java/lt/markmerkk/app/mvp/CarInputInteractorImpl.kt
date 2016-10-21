package lt.markmerkk.app.mvp

import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
class CarInputInteractorImpl(
        private val car: Car
) : CarInputInteractor {
    override fun moveForward() {
        car.accelerate = Car.ACC_FORWARD
    }

    override fun moveBackward() {
        car.accelerate = Car.ACC_BACKWARD
    }

    override fun moveNone() {
        car.accelerate = Car.ACC_NONE
    }

    override fun steerLeft() {
        car.steer = Car.STEER_LEFT
    }

    override fun steerRight() {
        car.steer = Car.STEER_RIGHT
    }

    override fun steerNone() {
        car.steer = Car.STEER_NONE
    }
}