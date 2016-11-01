package lt.markmerkk.app.mvp

import com.badlogic.gdx.Input

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
class InputPresenterImpl(
        private val input: Input
) : InputPresenter {

    override var carInputInteractor: CarInputInteractor? = null

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render() {
        handleSteer()
        handleMove()
    }

    fun handleSteer() {
        val inputInteractor = carInputInteractor
        if (inputInteractor == null) return
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            inputInteractor.steerLeft()
        } else if (input.isKeyPressed(Input.Keys.RIGHT)) {
            inputInteractor.steerRight()
        } else {
            inputInteractor.steerNone()
        }
    }

    fun handleMove() {
        val inputInteractor = carInputInteractor
        if (inputInteractor == null) return
        if (input.isKeyPressed(Input.Keys.UP)) {
            inputInteractor.moveForward()
        } else if(input.isKeyPressed(Input.Keys.DOWN)) {
            inputInteractor.moveBackward()
        } else {
            inputInteractor.moveNone()
        }
    }

}