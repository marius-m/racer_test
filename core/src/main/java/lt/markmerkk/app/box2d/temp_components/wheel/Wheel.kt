package lt.markmerkk.app.box2d.temp_components.wheel

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.box2d.CarImpl

/**
 * @author mariusmerkevicius
 * @since 2016-08-28
 */
interface Wheel {
    val world: World
    val body: Body
    val car: Car
    var posX: Float
    var posY: Float
    val width: Float
    val height: Float

    val powered: Boolean

    fun directionVector(): Vector2
    fun localVelocity(): Vector2
    fun changeAngle(angle: Float)
    fun killSidewayVector()
}