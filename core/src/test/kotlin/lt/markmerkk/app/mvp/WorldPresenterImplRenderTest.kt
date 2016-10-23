package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-23
 */
class WorldPresenterImplRenderTest {

    val worldInteractor: WorldInteractor = mock()
    val presenter = WorldPresenterImpl(worldInteractor)

    @Test
    fun test_input_should() {
        // Arrange
        // Act
        presenter.render(1f)

        // Assert
        verify(worldInteractor).step(any())
    }
}