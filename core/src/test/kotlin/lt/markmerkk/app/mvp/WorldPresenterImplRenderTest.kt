package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-23
 */
class WorldPresenterImplRenderTest {

    val worldInteractor: WorldInteractor = mock()

    @Test
    fun isHost_shouldStep() {
        // Arrange
        val presenter = WorldPresenterImpl(
                isHost = true,
                worldInteractor = worldInteractor
        )

        // Act
        presenter.render(1f)

        // Assert
        verify(worldInteractor).step(any())
    }

    @Test
    fun notHost_idle() {
        // Arrange
        val presenter = WorldPresenterImpl(
                isHost = false,
                worldInteractor = worldInteractor
        )

        // Act
        presenter.render(1f)

        // Assert
        verify(worldInteractor, never()).step(any())
    }
}