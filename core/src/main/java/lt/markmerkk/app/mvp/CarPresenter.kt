package lt.markmerkk.app.mvp

import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
interface CarPresenter : Presenter {
    fun addCar(car: Car)
    fun removeCar(car: Car)
    fun render(deltaTime: Float)
}