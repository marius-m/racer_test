package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-11-01
 */
class ClientPresenterImplSendHelloTest {
    val view: ClientView = mock()
    val playerInteractor: PlayerInteractor = mock()
    val interactor: ClientInteractor = mock()

    @Test
    fun connected_sendHello() {
        // Arrange
        var presenter = ClientPresenterImpl(
                false,
                view,
                interactor,
                playerInteractor,
                emptyList()
        )

        // Act
        presenter.onConnected(1)

        // Assert
        verify(interactor).sendHello()
    }

    @Test
    fun disconnected_noTrigger() {
        // Arrange
        var presenter = ClientPresenterImpl(
                false,
                view,
                interactor,
                playerInteractor,
                emptyList()
        )

        // Act
        presenter.onDisconnected(1)

        // Assert
        verify(interactor, never()).sendHello()
    }

}