package lt.markmerkk.app.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import lt.markmerkk.app.box2d.temp_components.Wheel
import java.util.*

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class Car(
        val world: World,
        val position: Vector2,
        val angle: Float
) {
    val body : Body
    val width = 1f
    val height = 2f

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

        val wheels = listOf<Wheel>(
                Wheel(world = world, car = this,
                        posX = -0.5f, posY = -0.6f,
                        width = 0.2f, height = 0.4f),
                Wheel(world = world, car = this,
                        posX = 0.5f, posY = -0.6f,
                        width = 0.2f, height = 0.4f),
                Wheel(world = world, car = this,
                        posX = -0.5f, posY = 0.6f,
                        width = 0.2f, height = 0.4f),
                Wheel(world = world, car = this,
                        posX = 0.5f, posY = 0.6f,
                        width = 0.2f, height = 0.4f)
        )
    }

    fun update(deltaTime: Float) {

    }
}