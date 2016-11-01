package lt.markmerkk.app.box2d

/**
 * @author mariusmerkevicius
 * @since 2016-11-01
 */
class CarBridgeEmptyImpl : CarBridge {

    override val car: Car
        get() = throw IllegalStateException("Cannot provide a car impl in an empty shell")

    override val x: Float = 0.0f

    override val y: Float = 0.0f

    override val angle: Float = 0.0f

    override fun update(deltaTime: Float) { }

    override fun destroy() { }
}