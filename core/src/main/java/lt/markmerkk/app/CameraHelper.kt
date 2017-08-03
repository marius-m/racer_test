package lt.markmerkk.app

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Matrix4
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-06-04
 *
 * Responsible for moving user camera
 */
class CameraHelper(
        private val virtualWidth : Int,
        private val virtualHeight : Int
) {
    var viewportWidth: Int = 0
    var viewportHeight: Int = 0
    val combine: Matrix4
        get() = camera.combined

    private val screenWidth: Int
    private val screenHeight: Int
    private val aspect: Float

    private val camera: OrthographicCamera

    init {
        camera = OrthographicCamera()

        screenWidth = Gdx.graphics.width
        screenHeight = Gdx.graphics.height

        aspect = (virtualWidth / virtualHeight).toFloat()

        if (screenWidth / screenHeight >= aspect) {
            viewportHeight = virtualHeight
            viewportWidth = viewportHeight * screenWidth / screenHeight
        } else {
            viewportWidth = virtualWidth
            viewportHeight = viewportWidth * screenHeight / screenWidth
        }

        camera.setToOrtho(false, viewportWidth.toFloat(), viewportHeight.toFloat())
        logger.debug(String.format(
                "Screen: virtualWidth: %d x %d",
                virtualWidth,
                virtualHeight
        ))
        logger.debug(String.format(
                "Screen: screen: %d x %d",
                screenWidth,
                screenHeight
        ))
        logger.debug(String.format(
                "Screen: view port: %d x %d",
                viewportWidth,
                viewportHeight
        ))
    }

    fun update(x : Float, y : Float) {
        camera.position.set(x, y, 0f)
        camera.update()
    }

    companion object {
        val logger = LoggerFactory.getLogger(CameraHelper::class.java)
    }

}