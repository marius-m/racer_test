package lt.markmerkk.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.box2d.BoxProperty
import lt.markmerkk.app.box2d.Car
import lt.markmerkk.app.box2d.temp_components.PenComponent
import lt.markmerkk.app.box2d.temp_components.WallComponent

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 */
class GameScreen : Screen {

    private var worldWidth: Float = 0.toFloat()
    private var worldHeight: Float = 0.toFloat()

    lateinit var spriteBatch : SpriteBatch
    lateinit var camera: CameraHelper
    lateinit var world : World
    lateinit var debugMatrix : Matrix4
    lateinit var debugRenderer: Box2DDebugRenderer

    lateinit var car: Car // Should be extracted later

    fun create() {
        world = World(Vector2(0.0f, 0.0f), true)

        camera = CameraHelper(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)
        car = Car(world, Vector2(20f, 10f), Math.PI.toFloat())

        worldWidth = camera.viewportWidth.toFloat() / PIXELS_PER_METER
        worldHeight = camera.viewportHeight.toFloat() / PIXELS_PER_METER

        spriteBatch = SpriteBatch()
        debugRenderer = Box2DDebugRenderer()
        //spriteBatch.projectionMatrix = camera.combine
        debugMatrix = camera.combine
        debugMatrix.scale(
                PIXELS_PER_METER.toFloat(),
                PIXELS_PER_METER.toFloat(),
                PIXELS_PER_METER.toFloat()
        )

        val componentPen = PenComponent(world, worldWidth, worldHeight) // todo : This should be exported in the long run
        val componentWall = WallComponent(world, worldWidth, worldHeight)// todo : This should be exported in the long run
    }

    // Callback methods

    override fun pause() { }

    override fun resume() { }

    override fun show() { }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //camera.update(car.body.getPosition().x * PIXELS_PER_METER, car.body.getPosition().y * PIXELS_PER_METER)

        world.step(Gdx.app.graphics.deltaTime, 3, 3)
        world.clearForces()

        spriteBatch.begin()
        debugRenderer.render(world, debugMatrix)
        spriteBatch.end()
    }

    override fun resize(width: Int, height: Int) { }

    override fun hide() { }

    override fun dispose() {
        spriteBatch.dispose()
    }

    companion object {
        const val VIRTUAL_WIDTH = 480
        const val VIRTUAL_HEIGHT = 320
        const val PIXELS_PER_METER = 16
    }
}