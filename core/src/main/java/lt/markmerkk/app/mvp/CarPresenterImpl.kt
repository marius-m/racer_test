package lt.markmerkk.app.mvp

import com.badlogic.gdx.graphics.g2d.Sprite
import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
// todo : Not sure this is needed at all
class CarPresenterImpl(
        private val car: Car
) : CarPresenter {

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render(deltaTime: Float) {
        car.update(deltaTime)
    }

}