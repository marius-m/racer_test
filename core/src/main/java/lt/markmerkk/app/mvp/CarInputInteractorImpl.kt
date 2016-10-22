package lt.markmerkk.app.mvp

import lt.markmerkk.app.box2d.CarImpl

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
class CarInputInteractorImpl(
        private val car: CarImpl
) : CarInputInteractor {
    override fun moveForward() {
        car.accelerate = CarImpl.ACC_FORWARD
    }

    override fun moveBackward() {
        car.accelerate = CarImpl.ACC_BACKWARD
    }

    override fun moveNone() {
        car.accelerate = CarImpl.ACC_NONE
    }

    override fun steerLeft() {
        car.steer = CarImpl.STEER_LEFT
    }

    override fun steerRight() {
        car.steer = CarImpl.STEER_RIGHT
    }

    override fun steerNone() {
        car.steer = CarImpl.STEER_NONE
    }
}