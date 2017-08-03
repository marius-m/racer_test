package lt.markmerkk.app.box2d.temp_components.wheel

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import lt.markmerkk.app.box2d.CarBox2D

/**
 * @author mariusmerkevicius
 * @since 2016-08-28
 */
abstract class BaseWheelImpl(
        override val world: World,
        override val carBox2D: CarBox2D,
        override var posX: Float,
        override var posY: Float,
        override val width: Float,
        override val height: Float,
        override val powered: Boolean
) : Wheel {

    override val body: Body

    init {
        //init body
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(carBox2D.body.getWorldPoint(Vector2(posX, posY)))
        bodyDef.angle = carBox2D.body.angle
        this.body = world.createBody(bodyDef)

        //init shape
        val fixtureDef = FixtureDef()
        fixtureDef.density = 1.0f
        fixtureDef.isSensor = true

        val wheelShape = PolygonShape()
        wheelShape.setAsBox(width / 2, height / 2)
        fixtureDef.shape = wheelShape

        this.body.createFixture(fixtureDef)
        wheelShape.dispose()

        initJoint(world, carBox2D)
    }

    abstract fun initJoint(world: World, carBox2D: CarBox2D)

    override fun localVelocity(): Vector2 {
        return carBox2D.body.getLocalVector(carBox2D.body.getLinearVelocityFromLocalPoint(body.position))
    }

    override fun directionVector(): Vector2 {
        val directionVector: Vector2
        if (localVelocity().y > 0)
            directionVector = Vector2(0f, 1f)
        else
            directionVector = Vector2(0f, -1f)
        return directionVector.rotate(Math.toDegrees(body.angle.toDouble()).toFloat())
    }

    override fun changeAngle(angle: Float) {
        body.setTransform(body.position, carBox2D.body.angle + Math.toRadians(angle.toDouble()).toFloat())
    }

    override fun killSidewayVector() {
        body.linearVelocity = killedLocalVelocity()
    }

    //region Convenience

    private fun killedLocalVelocity(): Vector2 {
        val velocity = body.linearVelocity
        val sidewaysAxis = directionVector()
        val dotprod = velocity.dot(sidewaysAxis)
        return Vector2(sidewaysAxis.x * dotprod, sidewaysAxis.y * dotprod)
    }

    //endregion

}