package lt.markmerkk.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.entities.PlayerClient
import lt.markmerkk.app.mvp.*
import lt.markmerkk.app.mvp.painter.SpritesPresenter
import lt.markmerkk.app.mvp.painter.SpritesPresenterImpl
import lt.markmerkk.app.mvp.painter.SpritesView
import rx.concurrency.GdxScheduler
import rx.schedulers.Schedulers

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
    private val playerPresenter: PlayerPresenter<PlayerClient> = PlayerPresenterClientImpl(players)
    private val clientPresenter: ClientPresenter = ClientPresenterImpl(
            clientInteractor = ClientInteractorImpl(),
            playerPresenter = playerPresenter,
            players = players,
            uiScheduler = GdxScheduler.get(),
            ioScheduler = Schedulers.io()
    )

    private val inputPresenter: InputPresenter = InputPresenterImpl(Gdx.input)

    fun create() {
        spritesPresenter.onAttach()
        inputPresenter.onAttach()
        clientPresenter.onAttach()
        playerPresenter.onAttach()

        // Adding a test car
//        if (isHost) {
//            val player = playerPresenter.createPlayer(connectionId = -1)
//            playerPresenter.addPlayer(player)
//            inputPresenter.carInputInteractor = CarInputInteractorImpl(player.carBridge.car)
//        }
    }

    //region Callback methods

    override fun pause() {
    }

    override fun resume() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        spritesPresenter.render()
        inputPresenter.render()
        clientPresenter.update()
        playerPresenter.render(delta)
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
    }

    override fun dispose() {
        playerPresenter.onDetach()
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