package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-23
 */
class ServerPresenterImplTest {
    val serverInteractor: ServerInteractor = mock()
    val presenter = ServerPresenterImpl(serverInteractor)

    @Test
    fun onAttach_start() {
        // Arrange
        // Act
        presenter.onAttach()

        // Assert
        verify(serverInteractor).start()
    }

    @Test
    fun onDetach_stop() {
        // Arrange
        // Act
        presenter.onDetach()

        // Assert
        verify(serverInteractor).stop()
    }

}