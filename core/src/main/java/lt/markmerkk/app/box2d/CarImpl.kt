package lt.markmerkk.app.box2d

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import lt.markmerkk.app.box2d.temp_components.wheel.RevolvingWheelImpl
import lt.markmerkk.app.box2d.temp_components.wheel.Wheel
import lt.markmerkk.app.box2d.temp_components.wheel.WheelImpl

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class CarImpl(
        val world: World,
        val position: Vector2
) : Car {
    override val body : Body
    val width = 1f
    val height = 2f
    val maxSteerAngle = 30f
    val minSteerAngle = 15f
    val power = 5f
    val maxSpeed = 20f
    val angle = Math.PI.toFloat()
    lateinit var wheels: List<Wheel>

    var steer = STEER_NONE
    var accelerate = ACC_NONE
    var wheelAngle: Float = 0f
    var speed = 0f
        set(value) {
            var velocity = body.linearVelocity
            velocity = velocity.nor()
            velocity = Vector2(
                    velocity.x * (speed * 1000.0f / 3600.0f),
                    velocity.y * (speed * 1000.0f / 3600.0f)
            )
            this.body.linearVelocity = velocity
        }

    init {
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(position)
        bodyDef.angle = angle
        body = world.createBody(bodyDef)

        val fixtureDef = FixtureDef()
        fixtureDef.density = 1.0f
        fixtureDef.friction = 0.1f
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

    fun getSpeedKMH(): Float {
        val velocity = this.body.linearVelocity
        val len = velocity.len()
        return len / 1000 * 3600
    }

    fun getLocalVelocity(): Vector2 {
        return this.body.getLocalVector(body.getLinearVelocityFromLocalPoint(Vector2(0f, 0f)))
    }

    override fun update(deltaTime: Float) {
        wheels.forEach { it.killSidewayVector() }

        val increase = maxSteerAngle * deltaTime * 5f
        when (steer) {
            STEER_LEFT -> wheelAngle = Math.min((Math.max(wheelAngle, 0f) + increase).toFloat(), maxSteerAngle)
            STEER_RIGHT -> wheelAngle = Math.max(Math.min(wheelAngle, 0f) - increase, -maxSteerAngle)
            else -> wheelAngle = 0f
        }
        wheels.filterIsInstance<RevolvingWheelImpl>()
                .forEach { it.changeAngle(wheelAngle) }

        var baseVector: Vector2
        if (accelerate == ACC_FORWARD && this.getSpeedKMH() < this.maxSpeed) {
            baseVector = Vector2(0f, -1f)
        } else if (accelerate == ACC_BACKWARD) {
            if (getLocalVelocity().y < 0) {
                baseVector = Vector2(0f, 1.3f)
            } else {
                baseVector = Vector2(0f, 0.7f)
            }
        } else if (accelerate == ACC_NONE) {
            baseVector = Vector2(0f, 0f)
            if (getSpeedKMH() < 7) {
                speed = 0f
            } else if (this.getLocalVelocity().y < 0) {
                baseVector = Vector2(0f, 0.7f)

            } else if (this.getLocalVelocity().y > 0) {
                baseVector = Vector2(0f, -0.7f)

            }
        } else {
            baseVector = Vector2(0f, 0f)
        }

        val forceVector = Vector2(power * baseVector.x, power * baseVector.y)
        wheels.filterIsInstance<RevolvingWheelImpl>()
                .forEach {
                    val position = it.body.worldCenter
                    it.body.applyForce(
                            it.body.getWorldVector(Vector2(forceVector.x, forceVector.y)),
                            position,
                            true
                    )
                }
    }

    override fun destroy() {
        world.destroyBody(body)
        wheels.forEach { world.destroyBody(it.body) }
    }

    companion object {
        private val TWO_PI = 2 * Math.PI

        const val STEER_NONE = 0
        const val STEER_LEFT = 1
        const val STEER_RIGHT = 2

        const val ACC_NONE = 0
        const val ACC_FORWARD = 1
        const val ACC_BACKWARD = 2
    }

}