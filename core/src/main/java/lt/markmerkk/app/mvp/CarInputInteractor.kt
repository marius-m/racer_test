package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-21
 */
interface CarInputInteractor {
    fun moveForward()
    fun moveBackward()
    fun moveNone()

    fun steerLeft()
    fun steerRight()
    fun steerNone()
}