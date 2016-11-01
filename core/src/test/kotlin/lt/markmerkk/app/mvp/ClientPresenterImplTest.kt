package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.any
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
    val view: ClientView = mock()
    val eventProvider: NetworkEventProvider = mock()
    val clientInteractor: ClientInteractor = mock()

    @Test
    fun onAttach_host_start() {
        // Arrange
        val presenter = ClientPresenterImpl(true, view, clientInteractor, emptyList())

        // Act
        presenter.onAttach()

        // Assert
        verify(clientInteractor, never()).start(any())
    }

    @Test
    fun onAttach_notHost_idle() {
        // Arrange
        val presenter = ClientPresenterImpl(false, view, clientInteractor, emptyList())

        // Act
        presenter.onAttach()

        // Assert
        verify(clientInteractor).start(any())
    }

    @Test
    fun onDetach_host_stop() {
        // Arrange
        val presenter = ClientPresenterImpl(true, view, clientInteractor, emptyList())

        // Act
        presenter.onDetach()

        // Assert
        verify(clientInteractor, never()).stop()
    }

    @Test
    fun onDetach_notHost_idle() {
        // Arrange
        val presenter = ClientPresenterImpl(false, view, clientInteractor, emptyList())

        // Act
        presenter.onDetach()

        // Assert
        verify(clientInteractor).stop()
    }

}