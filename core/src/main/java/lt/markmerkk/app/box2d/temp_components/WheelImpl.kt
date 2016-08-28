package lt.markmerkk.app.box2d.temp_components

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import lt.markmerkk.app.box2d.Car

/**
 * @author mariusmerkevicius
 * @since 2016-08-28
 */
class WheelImpl(
        world: World,
        car: Car,
        posX: Float,
        posY: Float,
        width: Float,
        height: Float,
        powered: Boolean
) : BaseWheelImpl(world, car, posX, posY, width, height, powered) {

    override fun initJoint(world: World, car: Car) {
        val jointdef = PrismaticJointDef()
        jointdef.initialize(car.body, body, body.worldCenter, Vector2(1f, 0f))
        jointdef.enableLimit = true
        jointdef.lowerTranslation = 0f
        jointdef.upperTranslation = 0f
        world.createJoint(jointdef)
    }
}