package lt.markmerkk.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.box2d.CarImpl
import lt.markmerkk.app.factory.PhysicsComponentFactory
import lt.markmerkk.app.mvp.*
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractor
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractorImpl
import lt.markmerkk.app.mvp.painter.SpritesPresenterImpl
import lt.markmerkk.app.mvp.painter.SpritesView

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class GameScreen(
        private val isHost: Boolean = true
) : Screen, SpritesView, WorldView, DebugView, InputView, CarView, ClientView {

    private val camera: CameraHelper = CameraHelper(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)
    private val world: World = World(Vector2(0.0f, 0.0f), true)
    private val componentFactory = PhysicsComponentFactory(world, camera)

    private val spriteBundleInteractors = mutableListOf<SpriteBundleInteractor>()
    val carPresenter = CarPresenterImpl(spriteBundleInteractors)
    val spritesPresenter = SpritesPresenterImpl(
            camera,
            SpriteBatch(),
            spriteBundleInteractors
    )
    val worldPresenter = WorldPresenterImpl(isHost, WorldInteractorImpl(world))
    val debugPresenter = DebugPresenterImpl(world, camera)
    val inputPresenter = InputPresenterImpl(Gdx.input)
    val serverPresenter = ServerPresenterImpl(
            isHost,
            ServerInteractorImpl(),
            spriteBundleInteractors
    )
    val clientPresenter = ClientPresenterImpl(isHost, ClientInteractorImpl())

    fun create() {
        spritesPresenter.onAttach()
        debugPresenter.onAttach()
        inputPresenter.onAttach()
        carPresenter.onAttach()
        serverPresenter.onAttach()
        clientPresenter.onAttach()

        componentFactory.createBoundWalls()
        componentFactory.createPen()

        // Adding a test car
        if (isHost) {
            val car = CarImpl(world, Vector2(20f, 10f))
            val carSprite = Sprite(Texture(Gdx.files.internal("data/car_small.png")))
            spriteBundleInteractors.add(SpriteBundleInteractorImpl(carSprite))
            carPresenter.addCar(car)
            inputPresenter.carInputInteractor = CarInputInteractorImpl(car)
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
        carPresenter.render(delta)
        serverPresenter.update()
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
        carPresenter.onDetach()
        serverPresenter.onDetach()
        clientPresenter.onDetach()
    }

    //endregion

    companion object {
        const val VIRTUAL_WIDTH = 480
        const val VIRTUAL_HEIGHT = 320
        const val PIXELS_PER_METER = 16
    }
}