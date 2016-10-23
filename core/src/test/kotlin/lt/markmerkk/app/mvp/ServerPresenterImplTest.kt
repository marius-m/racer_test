package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
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

    @Test
    fun onAttach_host_start() {
        // Arrange
        val presenter = ServerPresenterImpl(true, serverInteractor)

        // Act
        presenter.onAttach()

        // Assert
        verify(serverInteractor).start()
    }

    @Test
    fun onAttach_notHost_idle() {
        // Arrange
        val presenter = ServerPresenterImpl(false, serverInteractor)

        // Act
        presenter.onAttach()

        // Assert
        verify(serverInteractor, never()).start()
    }

    @Test
    fun onDetach_host_stop() {
        // Arrange
        val presenter = ServerPresenterImpl(true, serverInteractor)

        // Act
        presenter.onDetach()

        // Assert
        verify(serverInteractor).stop()
    }

    @Test
    fun onDetach_notHost_idle() {
        // Arrange
        val presenter = ServerPresenterImpl(false, serverInteractor)

        // Act
        presenter.onDetach()

        // Assert
        verify(serverInteractor, never()).stop()
    }

}