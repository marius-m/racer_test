package lt.markmerkk.app.mvp.interactors

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import lt.markmerkk.app.network.events.EventHello
import lt.markmerkk.app.network.events.NetworkEvent
import org.junit.Assert.*
import org.junit.Before
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
    fun invalidEvent_noTrigger() {
        // Arrange
        val invalidEvent: NetworkEvent = mock()

        // Act
        eventProvider.event(invalidEvent)

        // Assert
        verify(listener, never()).onClientHello()
    }

    @Test
    fun validEvent_hello_trigger() {
        // Arrange
        val event = EventHello()

        // Act
        eventProvider.event(event)

        // Assert
        verify(listener).onClientHello()
    }

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