package lt.markmerkk.app.mvp.interactors

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.NetworkEvent
import org.junit.Assert.*
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
    fun invalidEvent_noTrigger() {
        // Arrange
        val invalidEvent: NetworkEvent = mock()

        // Act
        eventProvider.event(invalidEvent)

        // Assert
        verify(listener, never()).onPlayersUpdate(any())
    }

    @Test
    fun playerUpdate_trigger() {
        // Arrange
        val event = EventPlayersUpdate()

        // Act
        eventProvider.event(event)

        // Assert
        verify(listener).onPlayersUpdate(any())
    }

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