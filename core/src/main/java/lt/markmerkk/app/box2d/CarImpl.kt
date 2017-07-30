package lt.markmerkk.app.box2d

/**
 * @author mariusmerkevicius
 * @since 2016-11-01
 */
class CarImpl(
        override val carBox2D: CarBox2D
) : Car {
    override val x: Float
        get() = carBox2D.body.position.x
    override val y: Float
        get() = carBox2D.body.position.y
    override val angle: Float
        get() = carBox2D.body.angle

    override fun update(deltaTime: Float) {
        carBox2D.update(deltaTime)
    }

    override fun destroy() {
        carBox2D.destroy()
    }
}