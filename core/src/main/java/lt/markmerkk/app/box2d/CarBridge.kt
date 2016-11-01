package lt.markmerkk.app.box2d

/**
 * @author mariusmerkevicius
 * @since 2016-11-01
 *
 * Provides a bridge between physical body and its use.
 * This will ensure additional layer instead of direct car use (and its full impl)
 */
interface CarBridge {
    val car: Car

    val x: Float
    val y: Float
    val angle: Float

    fun update(deltaTime: Float)
    fun destroy()
}