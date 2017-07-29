package lt.markmerkk.app

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.factory.PhysicsComponentFactory
import lt.markmerkk.app.mvp.*
import lt.markmerkk.app.mvp.painter.SpritesPresenter
import lt.markmerkk.app.mvp.painter.SpritesPresenterImpl
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
    private val camera: CameraHelper = CameraHelper(GameScreen.VIRTUAL_WIDTH, GameScreen.VIRTUAL_HEIGHT)

    private val world: World = World(Vector2(0.0f, 0.0f), true)
    private val componentFactory = PhysicsComponentFactory(world, camera)
    private val players = mutableListOf<Player>()
    private val playerInteractor: PlayerInteractor = PlayerInteractorImpl(
            world,
            players
    )
    private val worldPresenter: WorldPresenter = WorldPresenterImpl(WorldInteractorImpl(world))
    private val debugPresenter: DebugPresenter = DebugPresenterImpl(world, camera)
    private val serverPresenter: ServerPresenter = ServerPresenterImpl(
            serverInteractor = ServerInteractorImpl(),
            playerInteractor = playerInteractor,
            players = players,
            uiScheduler = GdxScheduler.get(),
            ioScheduler = Schedulers.io()
    )

    override fun create() {
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