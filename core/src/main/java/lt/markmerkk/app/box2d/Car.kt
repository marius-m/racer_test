package lt.markmerkk.app.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import lt.markmerkk.app.box2d.temp_components.wheel.RevolvingWheelImpl
import lt.markmerkk.app.box2d.temp_components.wheel.Wheel
import lt.markmerkk.app.box2d.temp_components.wheel.WheelImpl

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class Car(
        val world: World,
        val position: Vector2
) {
    val body : Body
    val width = 1f
    val height = 2f
    val maxSteerAngle = 25f
    val minSteerAngle = 15f
    val power = 60
    val maxSpeed = 100
    val angle = Math.PI.toFloat()
    lateinit var wheels: List<Wheel>

    var steer = STEER_NONE
    var wheelAngle: Float = 0f

    init {
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(position)
        bodyDef.angle = angle
        body = world.createBody(bodyDef)

        val fixtureDef = FixtureDef()
        fixtureDef.density = 1.0f
        fixtureDef.friction = 0.4f
        fixtureDef.restitution = 0.2f

        val carShape = PolygonShape()
        carShape.setAsBox(width / 2, height / 2)
        fixtureDef.shape = carShape
        body.createFixture(fixtureDef)

        wheels = listOf <Wheel>(
                RevolvingWheelImpl(world = world, car = this,
                        posX = -0.5f, posY = -0.6f,
                        width = 0.2f, height = 0.4f),
                RevolvingWheelImpl(world = world, car = this,
                        posX = 0.5f, posY = -0.6f,
                        width = 0.2f, height = 0.4f),
                WheelImpl(world = world, car = this,
                        posX = -0.5f, posY = 0.6f,
                        width = 0.2f, height = 0.4f),
                WheelImpl(world = world, car = this,
                        posX = 0.5f, posY = 0.6f,
                        width = 0.2f, height = 0.4f)
        )
    }

    fun update(deltaTime: Float) {
        wheels.forEach { it.killSidewayVector() }

        val increase = maxSteerAngle * deltaTime * 5f
        when (steer) {
            STEER_LEFT -> wheelAngle = Math.min((Math.max(wheelAngle, 0f) + increase).toFloat(), minSteerAngle)
            STEER_RIGHT -> wheelAngle = Math.max(Math.min(wheelAngle, 0f) - increase, -minSteerAngle)
            else -> wheelAngle = 0f
        }
        wheels.filterIsInstance<RevolvingWheelImpl>()
                .forEach { it.changeAngle(wheelAngle) }
    }

    companion object {
        const val STEER_NONE = 0
        const val STEER_LEFT = 1
        const val STEER_RIGHT = 2

        const val ACC_NONE = 0
        const val ACC_FORWARD = 1
        const val ACC_BACKWARD = 2
    }

}