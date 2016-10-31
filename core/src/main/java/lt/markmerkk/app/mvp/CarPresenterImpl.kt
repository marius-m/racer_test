package lt.markmerkk.app.mvp

import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractor
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractorImpl
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
// todo : Not sure this is needed at all
class CarPresenterImpl(
        private val spriteBundleInteractors: MutableList<SpriteBundleInteractor>
) : CarPresenter {

    private val cars = mutableListOf<Car>()

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render(deltaTime: Float) {
//        cars.forEach {
//            it.update(deltaTime)
//            val sprite = spriteBundleInteractors.first().sprite
//            spriteBundleInteractors.first().updatePosition(
//                    GameScreen.PIXELS_PER_METER * it.body.position.x - sprite.width / 2,
//                    GameScreen.PIXELS_PER_METER * it.body.position.y - sprite.height / 2
//            )
//            spriteBundleInteractors.first().updateAngle(
//                    Math.toDegrees(it.body.angle.toDouble()).toFloat()
//            )
//        }
    }

    override fun addCar(car: Car) {
//        cars.add(car)
    }

    override fun removeCar(car: Car) {
//        cars.remove(car)
    }

}