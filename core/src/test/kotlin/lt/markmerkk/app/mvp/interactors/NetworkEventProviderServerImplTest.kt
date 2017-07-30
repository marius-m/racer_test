package lt.markmerkk.app.mvp.interactors

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-30
 */
class NetworkEventProviderServerImplTest {

    val listener: ServerEventListener = mock()
    val eventProvider = NetworkEventProviderServerImpl(listener)

    @Test
    fun connected_trigger() {
        // Arrange
        // Act
        eventProvider.connected(111)

        // Assert
        verify(listener).onClientConnected(any())
    }

    @Test
    fun disconnected_trigger() {
        // Arrange
        // Act
        eventProvider.disconnected(111)

        // Assert
        verify(listener).onClientDisconnected(any())
    }

}