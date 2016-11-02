package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Test
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-23
 */
class ServerPresenterImplTest {
    val view: ServerView = mock()
    val eventProvider: NetworkEventProvider = mock()
    val playerInteractor: PlayerInteractor = mock()
    val serverInteractor: ServerInteractor = mock()

    @Test
    fun onAttach_host_start() {
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
        presenter.onAttach()

        // Assert
        verify(serverInteractor).start(any())
    }

    @Test
    fun onAttach_notHost_idle() {
        // Arrange
        val presenter = ServerPresenterImpl(
                false,
                view,
                serverInteractor,
                playerInteractor,
                emptyList(),
                Schedulers.immediate(),
                Schedulers.immediate()
        )

        // Act
        presenter.onAttach()

        // Assert
        verify(serverInteractor, never()).start(any())
    }

    @Test
    fun onDetach_host_stop() {
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
        presenter.onDetach()

        // Assert
        verify(serverInteractor).stop()
    }

    @Test
    fun onDetach_notHost_idle() {
        // Arrange
        val presenter = ServerPresenterImpl(
                false,
                view,
                serverInteractor,
                playerInteractor,
                emptyList(),
                Schedulers.immediate(),
                Schedulers.immediate()
        )

        // Act
        presenter.onDetach()

        // Assert
        verify(serverInteractor, never()).stop()
    }

}