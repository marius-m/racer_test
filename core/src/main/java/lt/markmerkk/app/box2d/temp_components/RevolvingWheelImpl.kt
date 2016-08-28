package lt.markmerkk.app.box2d.temp_components

import com.badlogic.gdx.math.Vector2
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
        height: Float,
        powered: Boolean
) : BaseWheelImpl(world, car, posX, posY, width, height, powered) {

    override fun initJoint(world: World, car: Car) {
        val jointdef = RevoluteJointDef()
        jointdef.initialize(car.body, body, body.worldCenter)
        jointdef.enableMotor = false
        world.createJoint(jointdef)
    }

}