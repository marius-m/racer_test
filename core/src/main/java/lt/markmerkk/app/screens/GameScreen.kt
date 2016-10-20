package lt.markmerkk.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
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
import lt.markmerkk.app.mvp.painter.SpritesPresenterImpl
import lt.markmerkk.app.mvp.painter.SpritesView

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class GameScreen : Screen, SpritesView {

    private var worldWidth: Float = 0.toFloat()
    private var worldHeight: Float = 0.toFloat()

    lateinit var camera: CameraHelper
    lateinit var world : World
    lateinit var debugMatrix : Matrix4
    lateinit var debugRenderer: Box2DDebugRenderer

    val spritesPresenter by lazy {
        SpritesPresenterImpl(
                camera,
                SpriteBatch(),
                car.sprite
        )
    }

    lateinit var car: Car // Should be extracted later

    fun create() {
        world = World(Vector2(0.0f, 0.0f), true)

        camera = CameraHelper(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)
        car = Car(world, Vector2(20f, 10f))
        car.sprite.setOrigin(
                (car.sprite.width / 2).toFloat(),
                (car.sprite.height / 2).toFloat()
        )

        worldWidth = camera.viewportWidth.toFloat() / PIXELS_PER_METER
        worldHeight = camera.viewportHeight.toFloat() / PIXELS_PER_METER

        val componentPen = PenComponent(world, worldWidth, worldHeight) // todo : This should be exported in the long run
        val componentWall = WallComponent(world, worldWidth, worldHeight)// todo : This should be exported in the long run

        spritesPresenter.onAttach()
        debugRenderer = Box2DDebugRenderer()
        debugMatrix = camera.combine
        debugMatrix.scale(
                PIXELS_PER_METER.toFloat(),
                PIXELS_PER_METER.toFloat(),
                PIXELS_PER_METER.toFloat()
        )
    }

    // Callback methods

    override fun pause() { }

    override fun resume() { }

    override fun show() { }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        world.step(Gdx.app.graphics.deltaTime, 3, 3)
        world.clearForces()

        debugRenderer.render(world, debugMatrix)

        car.sprite.setPosition(
                PIXELS_PER_METER * car.body.position.x - car.sprite.width / 2,
                PIXELS_PER_METER * car.body.position.y - car.sprite.height / 2
        )
        car.sprite.rotation = Math.toDegrees(car.body.angle.toDouble()).toFloat()

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            car.steer = Car.STEER_LEFT
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            car.steer = Car.STEER_RIGHT
        } else {
            car.steer = Car.STEER_NONE
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            car.accelerate = Car.ACC_FORWARD
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            car.accelerate = Car.ACC_BACKWARD
        } else {
            car.accelerate = Car.ACC_NONE
        }

        world.step(Gdx.app.graphics.deltaTime, 3, 3)
        world.clearForces()

        car.update(Gdx.app.graphics.deltaTime)
        spritesPresenter.render()
    }

    override fun resize(width: Int, height: Int) { }

    override fun hide() { }

    override fun dispose() {
        spritesPresenter.onDetach()
        car.texture.dispose()
    }

    companion object {
        const val VIRTUAL_WIDTH = 480
        const val VIRTUAL_HEIGHT = 320
        const val PIXELS_PER_METER = 16
    }
}