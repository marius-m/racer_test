package lt.markmerkk.app.box2d.temp_components.wheel

import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-06-05
 */
class RevolvingWheelImpl(
        world: World,
        car: Car,
        posX: Float,
        posY: Float,
        width: Float,
        height: Float
) : BaseWheelImpl(world, car, posX, posY, width, height, true) {

    override fun initJoint(world: World, car: Car) {
        val jointdef = RevoluteJointDef()
        jointdef.initialize(car.body, body, body.worldCenter)
        jointdef.enableMotor = false
        world.createJoint(jointdef)
    }

}