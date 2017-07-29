package lt.markmerkk.app.mvp

/**
 * Functions to move the [Car]
 */
interface CarInputInteractor {
    fun moveForward()
    fun moveBackward()
    fun moveNone()

    fun steerLeft()
    fun steerRight()
    fun steerNone()
}