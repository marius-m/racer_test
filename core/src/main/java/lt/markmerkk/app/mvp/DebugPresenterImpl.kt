package lt.markmerkk.app.mvp

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-20
 */
class DebugPresenterImpl(
        private val world: World,
        private val camera: CameraHelper
) : DebugPresenter {

    private val debugMatrix : Matrix4 = camera.combine
    private val debugRenderer: Box2DDebugRenderer = Box2DDebugRenderer()

    init {
        debugMatrix.scale(
                GameScreen.PIXELS_PER_METER.toFloat(),
                GameScreen.PIXELS_PER_METER.toFloat(),
                GameScreen.PIXELS_PER_METER.toFloat()
        )
    }

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render() {
        debugRenderer.render(world, debugMatrix)
    }

}