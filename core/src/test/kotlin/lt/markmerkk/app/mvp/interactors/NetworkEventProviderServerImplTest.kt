package lt.markmerkk.app.mvp.interactors

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import lt.markmerkk.app.network.events.EventRegister
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
        verify(listener, never()).onNewClient(any())
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

    @Test
    fun registerEvent_trigger() {
        // Arrange
        val registerEvent = EventRegister()
        registerEvent.id = 111
        registerEvent.name = "test_name"

        // Act
        eventProvider.event(registerEvent)

        // Assert
        verify(listener).onNewClient(any())
    }

}