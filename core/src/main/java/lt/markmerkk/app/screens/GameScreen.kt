package lt.markmerkk.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.factory.PhysicsComponentFactory
import lt.markmerkk.app.mvp.*
import lt.markmerkk.app.mvp.painter.SpritesPresenter
import lt.markmerkk.app.mvp.painter.SpritesPresenterImpl
import lt.markmerkk.app.mvp.painter.SpritesView

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class GameScreen(
        private val isHost: Boolean = true
) : Screen, SpritesView, WorldView, DebugView, InputView,
        CarView, ServerView, ClientView, PlayerView {

    private val camera: CameraHelper = CameraHelper(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)
    private val world: World = World(Vector2(0.0f, 0.0f), true)
    private val componentFactory = PhysicsComponentFactory(world, camera)

    private val players = mutableListOf<Player>()

    val spritesPresenter: SpritesPresenter = SpritesPresenterImpl(
            camera,
            SpriteBatch(),
            players
    )
    val worldPresenter: WorldPresenter = WorldPresenterImpl(isHost, WorldInteractorImpl(world))
    val debugPresenter: DebugPresenter = DebugPresenterImpl(world, camera)
    val inputPresenter: InputPresenter = InputPresenterImpl(Gdx.input)
    val serverPresenter: ServerPresenter = ServerPresenterImpl(
            isHost,
            this,
            ServerInteractorImpl(),
            players
    )
    val clientPresenter: ClientPresenter = ClientPresenterImpl(
            isHost,
            this,
            ClientInteractorImpl(),
            players
    )
    val playerInteractor: PlayerInteractor = PlayerInteractorImpl(
            isHost,
            world,
            players
    )
    val playerPresenter: PlayerPresenter = PlayerPresenterImpl(
            playerInteractor,
            players
    )

    fun create() {
        spritesPresenter.onAttach()
        debugPresenter.onAttach()
        inputPresenter.onAttach()
        serverPresenter.onAttach()
        clientPresenter.onAttach()
        playerPresenter.onAttach()

        componentFactory.createBoundWalls()
        componentFactory.createPen()

        // Adding a test car
        if (isHost) {
            val player = playerPresenter.createPlayer(connectionId = -1)
            playerPresenter.addPlayer(player)
            inputPresenter.carInputInteractor = CarInputInteractorImpl(player.carBridge.car)
        }
    }

    //region Callback methods

    override fun pause() {
    }

    override fun resume() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        worldPresenter.render(delta)
        spritesPresenter.render()
        debugPresenter.render()
        inputPresenter.render()
        serverPresenter.update()
        clientPresenter.update()
        playerPresenter.render(delta)
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
    }

    override fun dispose() {
        spritesPresenter.onDetach()
        worldPresenter.onDetach()
        debugPresenter.onDetach()
        inputPresenter.onDetach()
        serverPresenter.onDetach()
        clientPresenter.onDetach()
        playerPresenter.onDetach()
    }

    //endregion

    //region MVP

    override fun onClientConnected(id: Int) {
//        Gdx.app.postRunnable {
//            val newPlayer = playerPresenter.createPlayer(id)
//            playerPresenter.addPlayer(newPlayer)
//        }
    }

    override fun onClientDisconnected(id: Int) {
//        Gdx.app.postRunnable {
//            playerPresenter.removePlayerByConnectionId(id)
//        }
    }

    //endregion

    companion object {
        const val VIRTUAL_WIDTH = 480
        const val VIRTUAL_HEIGHT = 320
        const val PIXELS_PER_METER = 16
    }
}