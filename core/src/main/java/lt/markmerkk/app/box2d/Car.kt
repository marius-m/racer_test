package lt.markmerkk.app.box2d

interface Car {
    fun update(deltaTime: Float)
    fun destroy()

    // movement
    fun accForward()
    fun accBackward()
    fun accStop()

    // steering
    fun steerLeft()
    fun steerRight()
    fun steerNone()
}