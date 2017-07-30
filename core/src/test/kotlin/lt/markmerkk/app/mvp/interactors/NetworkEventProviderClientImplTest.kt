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
class NetworkEventProviderClientImplTest {

    val listener: ClientEventListener = mock()
    val eventProvider = NetworkEventProviderClientImpl(listener)

    @Test
    fun playerConnected_trigger() {
        // Arrange
        // Act
        eventProvider.connected(connectionId = 1)

        // Assert
        verify(listener).onConnected(any())
    }

    @Test
    fun playerDisconnected_trigger() {
        // Arrange
        // Act
        eventProvider.disconnected(connectionId = 1)

        // Assert
        verify(listener).onDisconnected(any())
    }
}