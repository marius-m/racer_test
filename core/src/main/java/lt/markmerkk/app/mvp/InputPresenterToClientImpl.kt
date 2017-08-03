package lt.markmerkk.app.mvp

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import lt.markmerkk.app.entities.Movement

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
class InputPresenterToClientImpl(
        private val input: Input,
        private val clientInteractor: ClientInteractor
) : InputPresenter {

    override fun onAttach() {
        input.inputProcessor = inputProcessor
    }

    override fun onDetach() {
        input.inputProcessor = null
    }

    override fun render() { }

    //region Listeners

    private val inputProcessor: InputProcessor = object : InputProcessor {
        override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

        override fun mouseMoved(screenX: Int, screenY: Int): Boolean = false

        override fun keyTyped(character: Char): Boolean = false

        override fun scrolled(amount: Int): Boolean = false

        override fun keyUp(keycode: Int): Boolean {
            when (keycode) {
                Input.Keys.UP -> clientInteractor.sendMovementEventCode(Movement.FORWARD_STOP)
                Input.Keys.DOWN -> clientInteractor.sendMovementEventCode(Movement.BACKWARD_STOP)
            }
            return false
        }

        override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false

        override fun keyDown(keycode: Int): Boolean {
            when (keycode) {
                Input.Keys.UP -> clientInteractor.sendMovementEventCode(Movement.FORWARD_START)
                Input.Keys.DOWN -> clientInteractor.sendMovementEventCode(Movement.BACKWARD_START)
            }
            return false
        }

        override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

    }

    //endregion

}