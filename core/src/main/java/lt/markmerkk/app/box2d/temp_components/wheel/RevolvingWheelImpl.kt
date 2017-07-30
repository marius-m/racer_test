package lt.markmerkk.app.box2d.temp_components.wheel

import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import lt.markmerkk.app.box2d.CarBox2D

/**
 * @author mariusmerkevicius
 * @since 2016-06-05
 */
class RevolvingWheelImpl(
        world: World,
        carBox2D: CarBox2D,
        posX: Float,
        posY: Float,
        width: Float,
        height: Float
) : BaseWheelImpl(world, carBox2D, posX, posY, width, height, true) {

    override fun initJoint(world: World, carBox2D: CarBox2D) {
        val jointdef = RevoluteJointDef()
        jointdef.initialize(carBox2D.body, body, body.worldCenter)
        jointdef.enableMotor = false
        world.createJoint(jointdef)
    }

}