package lt.markmerkk.app.box2d

import com.badlogic.gdx.physics.box2d.Body

/**
 * @author mariusmerkevicius
 * @since 2016-10-22
 */
interface Car {
    val body: Body
    fun update(deltaTime: Float)
    fun destroy()
}