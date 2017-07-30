package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.entities.PlayerServerImpl
import org.junit.Test
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-11-02
 */
class ServerPresenterImplOnConnectionTest {
    val playerProvider: PlayerProvider = mock()
    val playerPresenter: PlayerPresenter = mock()
    val serverInteractor: ServerInteractor = mock()
    val players = mutableListOf<Player>()
    val presenter = ServerPresenterImpl(
            serverInteractor,
            playerProvider,
            playerPresenter,
            Schedulers.immediate(),
            Schedulers.immediate()
    )

    @Test
    fun validConnection_createPlayer() {
        // Arrange
        val fakePlayer: Player = mock()
        whenever(playerProvider.create(any())).thenReturn(fakePlayer)

        // Act
        presenter.onClientConnected(1)

        // Assert
        verify(playerPresenter).addPlayer(any())
    }

    @Test
    fun validDisconnect_removePlayer() {
        // Arrange
        val fakePlayer1 = PlayerServerImpl(1, "test_1", mock())

        // Act
        presenter.onClientDisconnected(1)

        // Assert
        verify(playerPresenter).removePlayerByConnectionId(1)
    }

}