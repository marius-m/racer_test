package lt.markmerkk.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.badlogic.gdx.utils.viewport.ScreenViewport
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.box2d.temp_components.PenComponent
import lt.markmerkk.app.box2d.temp_components.WallComponent
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
class GameScreen : Screen, SpritesView, WorldView, DebugView, InputView, CarView {

    private val camera: CameraHelper = CameraHelper(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)
    private val world: World = World(Vector2(0.0f, 0.0f), true)
    private val componentFactory = PhysicsComponentFactory(world, camera)

    private val car = Car(world, Vector2(20f, 10f))

    private val spriteBundleInteractors = listOf<SpriteBundleInteractor>(
            SpriteBundleInteractorImpl(
                    car.body,
                    Sprite(Texture(Gdx.files.internal("data/car_small.png")))
            )
    )

    val carPresenter by lazy {
        CarPresenterImpl(
                listOf(
                        car
                )
        )
    }

    val spritesPresenter by lazy {
        SpritesPresenterImpl(
                camera,
                SpriteBatch(),
                spriteBundleInteractors
        )
    }

    val worldPresenter by lazy {
        WorldPresenterImpl(
                world,
                spriteBundleInteractors
        )
    }

    val debugPresenter by lazy {
        DebugPresenterImpl(world, camera)
    }

    val inputPresenter by lazy {
        InputPresenterImpl(
                Gdx.input,
                CarInputInteractorImpl(car)
        )
    }

    fun create() {
        componentFactory.createBoundWalls()
        componentFactory.createPen()

        spritesPresenter.onAttach()
        debugPresenter.onAttach()
        inputPresenter.onAttach()
        carPresenter.onAttach()
    }

    // Callback methods

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
    }

    companion object {
        const val VIRTUAL_WIDTH = 480
        const val VIRTUAL_HEIGHT = 320
        const val PIXELS_PER_METER = 16
    }
}