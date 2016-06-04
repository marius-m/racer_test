package lt.markmerkk.app.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

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
        //init body
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(position)
        bodyDef.angle = angle
        this.body = world.createBody(bodyDef)

        //init shape
        val fixtureDef = FixtureDef()
        fixtureDef.density = 1.0f
        fixtureDef.friction = 0.4f //friction when rubbing against other shapes
        fixtureDef.restitution = 0.2f //amount of force feedback when hitting something. >0 makes the car bounce off, it's fun!
        val carShape = PolygonShape()
        carShape.setAsBox(this.width / 2, this.height / 2)
        fixtureDef.shape = carShape
        this.body.createFixture(fixtureDef)

    }
}