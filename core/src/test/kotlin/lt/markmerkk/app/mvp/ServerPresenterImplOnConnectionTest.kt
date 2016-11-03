package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.entities.PlayerImpl
import org.junit.Assert.*
import org.junit.Test
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-11-02
 */
class ServerPresenterImplOnConnectionTest {
    val view: ServerView = mock()
    val eventProvider: NetworkEventProvider = mock()
    val playerInteractor: PlayerInteractor = mock()
    val serverInteractor: ServerInteractor = mock()
    val fakePlayer1 = PlayerImpl(1, "test_1", mock(), mock())
    val players = listOf(fakePlayer1)
    val presenter = ServerPresenterImpl(
            false,
            view,
            serverInteractor,
            playerInteractor,
            players,
            Schedulers.immediate(),
            Schedulers.immediate()
    )

    @Test
    fun validConnection_createPlayer() {
        // Arrange
        val fakePlayer: Player = mock()
        whenever(playerInteractor.createPlayer(any())).thenReturn(fakePlayer)

        // Act
        presenter.onClientConnected(1)

        // Assert
        verify(playerInteractor).createPlayer(1)
        verify(playerInteractor).addPlayer(any())
    }

    @Test
    fun validDisconnect_removePlayer() {
        // Arrange
        // Act
        presenter.onClientDisconnected(1)

        // Assert
        verify(playerInteractor).removePlayerByConnectionId(1)
    }

}