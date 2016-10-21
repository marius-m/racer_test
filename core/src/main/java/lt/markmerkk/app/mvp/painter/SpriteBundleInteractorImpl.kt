package lt.markmerkk.app.mvp.painter

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body

/**
 * @author mariusmerkevicius
 * @since 2016-10-22
 */
class SpriteBundleInteractorImpl(
        override val body: Body,
        override val sprite: Sprite
) : SpriteBundleInteractor {

    init {
        sprite.setOrigin(
                (sprite.width / 2).toFloat(),
                (sprite.height / 2).toFloat()
        )
    }

    override fun updatePosition(x: Float, y: Float) {
        sprite.setPosition(x, y)
    }

    override fun updateAngle(angle: Float) {
        sprite.rotation = angle
    }
}