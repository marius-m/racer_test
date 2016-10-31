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
class ClientPresenterImplTest {
    val clientInteractor: ClientInteractor = mock()

    @Test
    fun onAttach_host_start() {
        // Arrange
        val presenter = ClientPresenterImpl(true, clientInteractor, emptyList())

        // Act
        presenter.onAttach()

        // Assert
        verify(clientInteractor, never()).start()
    }

    @Test
    fun onAttach_notHost_idle() {
        // Arrange
        val presenter = ClientPresenterImpl(false, clientInteractor, emptyList())

        // Act
        presenter.onAttach()

        // Assert
        verify(clientInteractor).start()
    }

    @Test
    fun onDetach_host_stop() {
        // Arrange
        val presenter = ClientPresenterImpl(true, clientInteractor, emptyList())

        // Act
        presenter.onDetach()

        // Assert
        verify(clientInteractor, never()).stop()
    }

    @Test
    fun onDetach_notHost_idle() {
        // Arrange
        val presenter = ClientPresenterImpl(false, clientInteractor, emptyList())

        // Act
        presenter.onDetach()

        // Assert
        verify(clientInteractor).stop()
    }

}