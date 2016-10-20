package lt.markmerkk.app.factory

import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.CameraHelper
import lt.markmerkk.app.box2d.temp_components.PenComponent
import lt.markmerkk.app.box2d.temp_components.WallComponent
import lt.markmerkk.app.screens.GameScreen

/**
 * @author mariusmerkevicius
 * @since 2016-10-20
 * Contains component creation functions
 */
class PhysicsComponentFactory(
        private val world: World,
        private val camera: CameraHelper
) {

    private val width = camera.viewportWidth.toFloat() / GameScreen.PIXELS_PER_METER
    private val height = camera.viewportHeight.toFloat() / GameScreen.PIXELS_PER_METER

    /**
     * Creates bounds to contain objects
     */
    fun createBoundWalls() {
        WallComponent(world, width, height)
    }

    /**
     * Creates a pen in the middle
     */
    fun createPen() {
        PenComponent(world, width, height)
    }


}