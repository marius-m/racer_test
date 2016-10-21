package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.Sprite
import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
class CarPresenterImpl(
        private val car: Car,
        private val sprite: Sprite
) : CarPresenter {

    init {
        sprite.setOrigin(
                (sprite.width / 2).toFloat(),
                (sprite.height / 2).toFloat()
        )
    }

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render(deltaTime: Float) {
        sprite.setPosition(
                GameScreen.PIXELS_PER_METER * car.body.position.x - sprite.width / 2,
                GameScreen.PIXELS_PER_METER * car.body.position.y - sprite.height / 2
        )
        sprite.rotation = Math.toDegrees(car.body.angle.toDouble()).toFloat()
        car.update(deltaTime)
    }

}