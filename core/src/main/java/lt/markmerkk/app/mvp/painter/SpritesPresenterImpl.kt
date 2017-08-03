package lt.markmerkk.app.mvp.painter

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.entities.PlayerClient

class SpritesPresenterImpl(
        private val camera: CameraHelper,
        private val spriteBatch: SpriteBatch,
        private val players: List<PlayerClient>
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
        players.forEach { it.draw(spriteBatch) }
        spriteBatch.end()
    }

}