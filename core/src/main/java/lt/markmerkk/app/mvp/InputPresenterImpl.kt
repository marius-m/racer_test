package lt.markmerkk.app.mvp

import com.badlogic.gdx.Input
import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
class InputPresenterImpl(
        private val input: Input,
        private val carInputInteractor: CarInputInteractor?
) : InputPresenter {

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render() {
        handleSteer()
        handleMove()
    }

    fun handleSteer() {
        if (carInputInteractor == null) return
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            carInputInteractor.steerLeft()
        } else if (input.isKeyPressed(Input.Keys.RIGHT)) {
            carInputInteractor.steerRight()
        } else {
            carInputInteractor.steerNone()
        }
    }

    fun handleMove() {
        if (carInputInteractor == null) return
        if (input.isKeyPressed(Input.Keys.UP)) {
            carInputInteractor.moveForward()
        } else if(input.isKeyPressed(Input.Keys.DOWN)) {
            carInputInteractor.moveBackward()
        } else {
            carInputInteractor.moveNone()
        }
    }

}