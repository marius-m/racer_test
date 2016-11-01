package lt.markmerkk.app.box2d

/**
 * @author mariusmerkevicius
 * @since 2016-11-01
 */
class CarBridgeImpl(
        override val car: Car
) : CarBridge {
    override val x: Float
        get() = car.body.position.x
    override val y: Float
        get() = car.body.position.y
    override val angle: Float
        get() = car.body.angle

    override fun update(deltaTime: Float) {
        car.update(deltaTime)
    }

    override fun destroy() {
        car.destroy()
    }
}