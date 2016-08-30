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

    val screenViewport = ScreenViewport()
    lateinit var stage: Stage
    lateinit var touchpad: Touchpad

    lateinit var car: Car // Should be extracted later

    fun create() {
        world = World(Vector2(0.0f, 0.0f), true)

        camera = CameraHelper(VIRTUAL_WIDTH, VIRTUAL_HEIGHT)
        car = Car(world, Vector2(20f, 10f))

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

        //Create a touchpad skin
        val touchpadSkin = Skin()
        //Set background image
        touchpadSkin.add("touchBackground", Texture("data/touchBackground.png"))
        //Set knob image
        touchpadSkin.add("touchKnob", Texture("data/touchKnob.png"))
        //Create TouchPad Style
        val touchpadStyle = Touchpad.TouchpadStyle()
        //Create Drawable's from TouchPad skin
        val touchBackground = touchpadSkin.getDrawable("touchBackground")
        val touchKnob = touchpadSkin.getDrawable("touchKnob")
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground
        touchpadStyle.knob = touchKnob
        //Create new TouchPad with the created style
        touchpad = Touchpad(10.toFloat(), touchpadStyle)
        //setBounds(x,y,width,height)
        touchpad.setBounds(15.toFloat(), 15.toFloat(), 150.toFloat(), 150.toFloat())

        //Create a Stage and add TouchPad
        stage = Stage(screenViewport, spriteBatch)
        stage.addActor(touchpad)
        Gdx.input.inputProcessor = stage

        //Create block sprite
        val blockTexture = Texture(Gdx.files.internal("data/block.png"))
        val blockSprite = Sprite(blockTexture)
        //Set position to centre of the screen
        blockSprite.setPosition(Gdx.graphics.width / 2 - blockSprite.getWidth() / 2, Gdx.graphics.height / 2 - blockSprite.getHeight() / 2)
        val blockSpeed = 5
    }

    // Callback methods

    override fun pause() { }

    override fun resume() { }

    override fun show() { }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

//        camera.update(car.body.position.x * PIXELS_PER_METER, car.body.position.y * PIXELS_PER_METER)

        world.step(Gdx.app.graphics.deltaTime, 3, 3)
        world.clearForces()

        spriteBatch.begin()
        debugRenderer.render(world, debugMatrix)
        spriteBatch.end()

        println("KnobX: ${touchpad.knobPercentX} / KnobY: ${touchpad.knobPercentY}")

        if (touchpad.knobPercentX < -0.5f) {
            car.steer = Car.STEER_LEFT
        } else if (touchpad.knobPercentX > 0.5f) {
            car.steer = Car.STEER_RIGHT
        } else {
            car.steer = Car.STEER_NONE
        }

        if (touchpad.knobPercentY > 0.5f) {
            car.accelerate = Car.ACC_FORWARD
        } else if (touchpad.knobPercentY < -0.5f) {
            car.accelerate = Car.ACC_BACKWARD
        } else {
            car.accelerate = Car.ACC_NONE
        }

//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            car.steer = Car.STEER_LEFT
//        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            car.steer = Car.STEER_RIGHT
//        } else {
//            car.steer = Car.STEER_NONE
//        }

//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            car.accelerate = Car.ACC_FORWARD
//        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            car.accelerate = Car.ACC_BACKWARD
//        } else {
//            car.accelerate = Car.ACC_NONE
//        }

        world.step(Gdx.app.graphics.deltaTime, 3, 3)
        world.clearForces()

        car.update(Gdx.app.graphics.deltaTime)
        stage.act(delta)
        stage.draw()
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