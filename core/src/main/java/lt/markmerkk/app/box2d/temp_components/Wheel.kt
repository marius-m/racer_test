package lt.markmerkk.app.box2d.temp_components

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-06-05
 */
class Wheel(
        val world: World,
        val car: Car,
        val posX: Float,
        val posY: Float,
        val width: Float,
        val height: Float
) {
    val body: Body

    init {
        //init body
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(car.body.getWorldPoint(Vector2(posX, posY)))
        bodyDef.angle = car.body.angle
        this.body = world.createBody(bodyDef)

        //init shape
        val fixtureDef = FixtureDef()
        fixtureDef.density = 1.0f
        fixtureDef.isSensor = true //wheel does not participate in collision calculations: resulting complications are unnecessary

        val wheelShape = PolygonShape()
        wheelShape.setAsBox(width / 2, height / 2)
        fixtureDef.shape = wheelShape

        this.body.createFixture(fixtureDef)
        wheelShape.dispose()

        val jointdef = RevoluteJointDef()
        jointdef.initialize(car.body, body, body.worldCenter)
        jointdef.enableMotor = false
        world.createJoint(jointdef)
    }

    companion object {
        const val PIXELS_PER_METER = 60.0f
    }

}