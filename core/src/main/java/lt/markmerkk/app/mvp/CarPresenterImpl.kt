package lt.markmerkk.app.mvp

import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
// todo : Not sure this is needed at all
class CarPresenterImpl() : CarPresenter {

    val cars = mutableListOf<Car>()

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render(deltaTime: Float) {
        cars.forEach { it.update(deltaTime) }
    }

}