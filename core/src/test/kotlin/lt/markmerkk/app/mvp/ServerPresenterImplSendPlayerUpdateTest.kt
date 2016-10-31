package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.network.events.EventPlayersUpdate
import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentCaptor

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-31
 */
class ServerPresenterImplSendPlayerUpdateTest {
    val view: ServerView = mock()
    val serverInteractor: ServerInteractor = mock()

    @Test
    fun validPlayers_generatePlayers() {
        // Arrange
        val fakePlayer: Player = mock()
        val presenter = ServerPresenterImpl(true, view, serverInteractor, emptyList())
        val captor = ArgumentCaptor.forClass(EventPlayersUpdate::class.java)

        // Act
        presenter.sendPlayerUpdate(listOf(fakePlayer))

        // Assert
        verify(serverInteractor).sendPlayerUpdate(capture(captor))
        assertNotNull(captor.value)
    }

    @Test
    fun validPlayers_generateCorrectResult() {
        // Arrange
        val fakePlayer: Player = mock()
        whenever(fakePlayer.id).thenReturn(111)
        whenever(fakePlayer.name).thenReturn("test_name")
        val presenter = ServerPresenterImpl(true, view, serverInteractor, emptyList())
        val captor = ArgumentCaptor.forClass(EventPlayersUpdate::class.java)

        // Act
        presenter.sendPlayerUpdate(listOf(fakePlayer))

        // Assert
        verify(serverInteractor).sendPlayerUpdate(capture(captor))
        assertEquals(1, captor.value.reportPlayers.size)
        assertEquals(111, captor.value.reportPlayers.get(0).id)
        assertEquals("test_name", captor.value.reportPlayers.get(0).name)
    }

    @Test
    fun noPlayers_doNotSend() {
        // Arrange
        val presenter = ServerPresenterImpl(true, view, serverInteractor, emptyList())

        // Act
        presenter.sendPlayerUpdate(emptyList())

        // Assert
        verify(serverInteractor, never()).sendPlayerUpdate(any())
    }
}