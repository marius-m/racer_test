package lt.markmerkk.app.box2d.temp_components.wheel

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import lt.markmerkk.app.box2d.CarBox2D

/**
 * @author mariusmerkevicius
 * @since 2016-08-28
 */
class WheelImpl(
        world: World,
        carBox2D: CarBox2D,
        posX: Float,
        posY: Float,
        width: Float,
        height: Float
) : BaseWheelImpl(world, carBox2D, posX, posY, width, height, false) {

    override fun initJoint(world: World, carBox2D: CarBox2D) {
        val jointdef = PrismaticJointDef()
        jointdef.initialize(carBox2D.body, body, body.worldCenter, Vector2(1f, 0f))
        jointdef.enableLimit = true
        jointdef.lowerTranslation = 0f
        jointdef.upperTranslation = 0f
        world.createJoint(jointdef)
    }
}