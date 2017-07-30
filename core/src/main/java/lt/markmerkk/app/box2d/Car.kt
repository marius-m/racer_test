package lt.markmerkk.app.box2d

interface Car {
    val carBox2D: CarBox2D

    val x: Float
    val y: Float
    val angle: Float

    fun update(deltaTime: Float)
    fun destroy()
}