package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.ReportPlayer
import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentCaptor
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-31
 */
class ServerPresenterImplSendPlayerUpdateTest {
    val view: ServerView = mock()
    val playerInteractor: PlayerInteractor = mock()
    val serverInteractor: ServerInteractor = mock()

    @Test
    fun validPlayers_generatePlayers() {
        // Arrange
        val fakePlayer: Player = mock()
        val presenter = ServerPresenterImpl(
                true,
                view,
                serverInteractor,
                playerInteractor,
                emptyList(),
                Schedulers.immediate(),
                Schedulers.immediate()
        )
        val captor = ArgumentCaptor.forClass(List::class.java)

        // Act
        presenter.sendPlayerUpdate(listOf(fakePlayer))

        // Assert
        verify(serverInteractor).sendPlayerUpdate(capture(captor) as List<ReportPlayer>)
        assertNotNull(captor.value)
    }

    @Test
    fun validPlayers_generateCorrectResult() {
        // Arrange
        val fakePlayer: Player = mock()
        whenever(fakePlayer.id).thenReturn(111)
        whenever(fakePlayer.name).thenReturn("test_name")
        val presenter = ServerPresenterImpl(
                true,
                view,
                serverInteractor,
                playerInteractor,
                emptyList(),
                Schedulers.immediate(),
                Schedulers.immediate()
        )
        val captor = ArgumentCaptor.forClass(List::class.java)

        // Act
        presenter.sendPlayerUpdate(listOf(fakePlayer))

        // Assert
        verify(serverInteractor).sendPlayerUpdate(capture(captor) as List<ReportPlayer>)
        val reportPlayers = captor.value as List<ReportPlayer>
        assertEquals(1, reportPlayers.size)
        assertEquals(111, reportPlayers.get(0).id)
        assertEquals("test_name", reportPlayers.get(0).name)
    }

    @Test
    fun noPlayers_doNotSend() {
        // Arrange
        val presenter = ServerPresenterImpl(
                true,
                view,
                serverInteractor,
                playerInteractor,
                emptyList(),
                Schedulers.immediate(),
                Schedulers.immediate()
        )

        // Act
        presenter.sendPlayerUpdate(emptyList())

        // Assert
        verify(serverInteractor, never()).sendPlayerUpdate(any())
    }
}