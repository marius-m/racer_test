package lt.markmerkk.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.entities.PlayerClient
import lt.markmerkk.app.mvp.*
import lt.markmerkk.app.mvp.painter.SpritesPresenter
import lt.markmerkk.app.mvp.painter.SpritesPresenterImpl
import lt.markmerkk.app.mvp.painter.SpritesView

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class GameScreen(
        private val camera: CameraHelper
) : Screen, SpritesView, WorldView, DebugView, InputView,
        ServerView, ClientView {

    private val players = mutableListOf<PlayerClient>()
    private val spritesPresenter: SpritesPresenter = SpritesPresenterImpl(
            camera,
            SpriteBatch(),
            players
    )
    private val clientInteractor: ClientInteractor = ClientInteractorImpl()
    private val clientPresenter: ClientPresenter = ClientPresenterImpl(
            clientInteractor = clientInteractor,
            players = players
    )

    private val inputPresenter: InputPresenter = InputPresenterToClientImpl(Gdx.input, clientInteractor)

    //region Callback methods

    override fun show() {
        spritesPresenter.onAttach()
        inputPresenter.onAttach()
        clientPresenter.onAttach()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        clientPresenter.update()
        inputPresenter.render()
        spritesPresenter.render()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
    }

    override fun dispose() {
        clientPresenter.onDetach()
        inputPresenter.onDetach()
        spritesPresenter.onDetach()
    }

    //endregion

    companion object {
        const val VIRTUAL_WIDTH = 480
        const val VIRTUAL_HEIGHT = 320
        const val PIXELS_PER_METER = 16
    }
}