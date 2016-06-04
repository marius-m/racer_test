package lt.markmerkk.app.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 *
 * Building block for the box2d
 */
class BoxProperty(
        val world: World,
        val width: Float,
        val height: Float,
        val position: Vector2
) {
    val body: Body

    init {
        val bodyDef = BodyDef()
        bodyDef.position.set(position)
        bodyDef.angle = 0f
        bodyDef.fixedRotation = true
        body = world.createBody(bodyDef)

        val fixtureDef = FixtureDef()
        val boxShape = PolygonShape()
        boxShape.setAsBox(width / 2, height / 2)

        fixtureDef.shape = boxShape
        fixtureDef.restitution = 0.7f
        body.createFixture(fixtureDef)

        boxShape.dispose()
    }
}