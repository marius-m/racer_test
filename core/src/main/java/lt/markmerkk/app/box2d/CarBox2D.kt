package lt.markmerkk.app.box2d

import com.badlogic.gdx.physics.box2d.Body

/**
 * Represents physical car impl that can be moved in the [World]
 */
interface CarBox2D {
    val body: Body

    var accelerate: Int // Flag for an acceleration
    var steer: Int // Flag for a steer direction

    fun update(deltaTime: Float)
    fun destroy()

    companion object {
        const val STEER_NONE = 0
        const val STEER_LEFT = 1
        const val STEER_RIGHT = 2

        const val ACC_NONE = 0
        const val ACC_FORWARD = 1
        const val ACC_BACKWARD = 2
    }

}