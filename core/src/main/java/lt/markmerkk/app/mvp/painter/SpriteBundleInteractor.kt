package lt.markmerkk.app.mvp.painter

import com.badlogic.gdx.graphics.g2d.Sprite

/**
 * @author mariusmerkevicius
 * @since 2016-10-22
 * Bridge for sprite param update
 */
interface SpriteBundleInteractor {
    val sprite: Sprite

    fun updatePosition(x: Float, y: Float)
    fun updateAngle(angle: Float)
}