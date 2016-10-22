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
        cars.forEach {
            it.update(deltaTime)
            it.sprite.setPosition(
                    GameScreen.PIXELS_PER_METER * it.body.position.x - it.sprite.width / 2,
                    GameScreen.PIXELS_PER_METER * it.body.position.y - it.sprite.height / 2
            )
            it.sprite.rotation = Math.toDegrees(it.body.angle.toDouble()).toFloat()
        }
    }

    override fun addCar(car: Car) {
        cars.add(car)
        spriteBundleInteractors.add(
                SpriteBundleInteractorImpl(
                        car.sprite
                )
        )
    }

    override fun removeCar(car: Car) {
        cars.remove(car)
        spriteBundleInteractors.removeAll(
                spriteBundleInteractors.filter { it.sprite == car.sprite }
        )
    }

}