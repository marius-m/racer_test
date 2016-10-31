package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-31
 */
class ServerPresenterImplNewClientTest {
    val view: ServerView = mock()
    val serverInteractor: ServerInteractor = mock()

    @Test
    fun newClient_triggerView() {
        // Arrange
        val presenter = ServerPresenterImpl(true, view, serverInteractor, emptyList())

        // Act
        presenter.onNewClient(id = 111)

        // Assert
        verify(view).onClientConnected(any())
    }

}