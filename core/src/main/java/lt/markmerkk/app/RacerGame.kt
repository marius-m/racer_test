package lt.markmerkk.app

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.factory.PhysicsComponentFactory
import lt.markmerkk.app.mvp.*
import lt.markmerkk.app.screens.GameScreen
import rx.concurrency.GdxScheduler
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class RacerGame(
        private val isHost: Boolean
) : Game() {
    lateinit var camera: CameraHelper
    lateinit var world: World
    lateinit var componentFactory: PhysicsComponentFactory
    lateinit var worldPresenter: WorldPresenter
    lateinit var debugPresenter: DebugPresenter
    lateinit var playerProvider: PlayerProvider
    lateinit var serverPresenter: ServerPresenter

    private val playerPresenter: PlayerPresenter = PlayerPresenterImpl(mutableListOf())

    override fun create() {
        camera = CameraHelper(GameScreen.VIRTUAL_WIDTH, GameScreen.VIRTUAL_HEIGHT)
        world = World(Vector2(0.0f, 0.0f), true)
        componentFactory= PhysicsComponentFactory(world, camera)
        worldPresenter = WorldPresenterImpl(WorldInteractorImpl(world))
        debugPresenter = DebugPresenterImpl(world, camera)
        playerProvider = PlayerProviderServerImpl(world)
        serverPresenter = ServerPresenterImpl(
                serverInteractor = ServerInteractorImpl(),
                playerProvider = playerProvider,
                playerPresenter = playerPresenter,
                uiScheduler = GdxScheduler.get(),
                ioScheduler = Schedulers.io()
        )

        worldPresenter.onAttach()
        debugPresenter.onAttach()
        serverPresenter.onAttach()

        componentFactory.createBoundWalls()
        componentFactory.createPen()

        screen = GameScreen(camera).apply { create() }
    }

    override fun render() {
        super.render()
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        val deltaTime = Gdx.graphics.deltaTime

        worldPresenter.render(deltaTime)
        debugPresenter.render()
    }

    override fun dispose() {
        serverPresenter.onDetach()
        debugPresenter.onDetach()
        worldPresenter.onDetach()
        super.dispose()
    }

}