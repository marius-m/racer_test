package lt.markmerkk.app.box2d

interface Car {
    fun update(deltaTime: Float)
    fun destroy()
    fun positionX(): Float
    fun positionY(): Float
    fun angle(): Float

    // movement
    fun accForward()
    fun accBackward()
    fun accStop()

    // steering
    fun steerLeft()
    fun steerRight()
    fun steerNone()
}