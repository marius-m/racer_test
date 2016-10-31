package lt.markmerkk.app.mvp.interactors

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import lt.markmerkk.app.network.events.EventPlayerPosition
import lt.markmerkk.app.network.events.EventRegister
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
        verify(listener, never()).onPlayerUpdate(any(), any(), any())
    }

    @Test
    fun registerEvent_trigger() {
        // Arrange
        val event = EventPlayerPosition()

        // Act
        eventProvider.event(event)

        // Assert
        verify(listener).onPlayerUpdate(any(), any(), any())
    }
}