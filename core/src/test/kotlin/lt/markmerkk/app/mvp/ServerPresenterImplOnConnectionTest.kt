package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import lt.markmerkk.app.entities.PlayerServer
import org.junit.Test
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-11-02
 */
class ServerPresenterImplOnConnectionTest {
    val playerPresenterServer: PlayerPresenterServer = mock()
    val serverInteractor: ServerInteractor = mock()
    val presenter = ServerPresenterImpl(
            serverInteractor,
            playerPresenterServer
    )

    val players = mutableListOf<PlayerServer>()

    @Test
    fun validConnection_createPlayer() {
        // Arrange
        // Act
        presenter.serverEventListener.onClientConnected(1)

        // Assert
        verify(playerPresenterServer).createPlayerById(any())
    }

    @Test
    fun validConnection_sendPlayerReport() {
        // Arrange
        // Act
        presenter.serverEventListener.onClientConnected(1)

        // Assert
        verify(serverInteractor).sendPlayerRegister(any())
    }

    @Test
    fun validDisconnect_removePlayer() {
        // Arrange
        // Act
        presenter.serverEventListener.onClientDisconnected(1)

        // Assert
        verify(playerPresenterServer).removePlayerByConnectionId(1)
    }

    @Test
    fun validDisconnect_sendNewPlayerList() {
        // Arrange
        // Act
        presenter.serverEventListener.onClientDisconnected(1)

        // Assert
        verify(serverInteractor).sendPlayerRegister(any())
    }

}