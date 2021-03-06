package lt.markmerkk.app

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.WorldProviderImpl
import lt.markmerkk.app.entities.PlayerServer
import lt.markmerkk.app.factory.PhysicsComponentFactory
import lt.markmerkk.app.mvp.*
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class RacerGame(
        private val isHost: Boolean,
        private val serverIp: String = Const.DEFAULT_SERVER_IP
) : Game() {

    lateinit var camera: CameraHelper
    lateinit var world: World
    lateinit var componentFactory: PhysicsComponentFactory
    lateinit var worldPresenter: WorldPresenter
    lateinit var debugPresenter: DebugPresenter
    lateinit var serverPresenter: ServerPresenter
    lateinit var playerPresenterServer: PlayerPresenterServer

    private val players = mutableListOf<PlayerServer>()

    override fun create() {
        camera = CameraHelper(GameScreen.VIRTUAL_WIDTH, GameScreen.VIRTUAL_HEIGHT)
        world = World(Vector2(0.0f, 0.0f), true)
        componentFactory = PhysicsComponentFactory(world, camera)
        worldPresenter = WorldPresenterImpl(WorldInteractorImpl(world))
        debugPresenter = DebugPresenterImpl(world, camera)
        worldPresenter.onAttach()
        debugPresenter.onAttach()
        componentFactory.createBoundWalls()
        componentFactory.createPen()
        if (isHost) {
            playerPresenterServer = PlayerPresenterServerImpl(
                    WorldProviderImpl(world),
                    players
            )
            serverPresenter = ServerPresenterImpl(
                    serverInteractor = ServerInteractorImpl(),
                    playerPresenterServer = playerPresenterServer
            )
            serverPresenter.onAttach()
        }

        setScreen(GameScreen(camera))
    }

    override fun render() {
        super.render()
        val deltaTime = Gdx.graphics.deltaTime
        worldPresenter.render(deltaTime)
        debugPresenter.render()
        if (isHost) {
            serverPresenter.update()
            playerPresenterServer.render(deltaTime)
        }
    }

    override fun dispose() {
        debugPresenter.onDetach()
        worldPresenter.onDetach()
        if (isHost) {
            serverPresenter.onDetach()
        }
        super.dispose()
    }

}