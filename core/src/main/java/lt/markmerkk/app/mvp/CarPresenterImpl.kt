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
        // todo: Should be moved elsewhere
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            car.steer = Car.STEER_LEFT
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            car.steer = Car.STEER_RIGHT
        } else {
            car.steer = Car.STEER_NONE
        }

        // todo: Should be moved elsewhere
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            car.accelerate = Car.ACC_FORWARD
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            car.accelerate = Car.ACC_BACKWARD
        } else {
            car.accelerate = Car.ACC_NONE
        }

        sprite.setPosition(
                GameScreen.PIXELS_PER_METER * car.body.position.x - sprite.width / 2,
                GameScreen.PIXELS_PER_METER * car.body.position.y - sprite.height / 2
        )
        sprite.rotation = Math.toDegrees(car.body.angle.toDouble()).toFloat()
        car.update(deltaTime)
    }

}