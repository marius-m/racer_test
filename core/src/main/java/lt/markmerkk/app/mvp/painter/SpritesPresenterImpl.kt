package lt.markmerkk.app.mvp.painter

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import lt.markmerkk.app.CameraHelper

/**
 * @author mariusmerkevicius
 * @since 2016-10-20
 */
class SpritesPresenterImpl(
        private val camera: CameraHelper,
        private val spriteBatch: SpriteBatch,
        private val spriteBundleInteractors: List<SpriteBundleInteractor>
) : SpritesPresenter {

    init {
        spriteBatch.projectionMatrix = camera.combine
    }

    override fun onAttach() {
    }

    override fun onDetach() {
        spriteBatch.dispose()
    }

    override fun render() {
        spriteBatch.begin()
        spriteBundleInteractors.forEach { it.sprite.draw(spriteBatch) }
        spriteBatch.end()
    }

}