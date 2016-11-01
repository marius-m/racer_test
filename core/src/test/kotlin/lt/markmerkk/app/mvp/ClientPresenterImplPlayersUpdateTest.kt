package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.network.events.ReportPlayer
import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-11-01
 */
class ClientPresenterImplPlayersUpdateTest {
    val view: ClientView = mock()
    val interactor: ClientInteractor = mock()

    @Test
    fun noPlayers_newPlayer_createNew() {
        // Arrange
        var presenter = ClientPresenterImpl(
                false,
                view,
                interactor,
                emptyList()
        )
        val reportPlayer = ReportPlayer().apply {
            id = 10
            name = "test_name"
        }

        // Act
        presenter.onPlayersUpdate(listOf(
                reportPlayer
        ))

        // Assert
        verify(view).onClientConnected(10)
    }

    @Test
    fun noPlayers_newPlayersMore_createNew() {
        // Arrange
        var presenter = ClientPresenterImpl(
                false,
                view,
                interactor,
                emptyList()
        )
        val reportPlayer1 = ReportPlayer().apply {
            id = 10
            name = "test_name"
        }
        val reportPlayer2 = ReportPlayer().apply {
            id = 11
            name = "test_name_2"
        }
        val reportPlayer3 = ReportPlayer().apply {
            id = 12
            name = "test_name_3"
        }

        // Act
        presenter.onPlayersUpdate(listOf(
                reportPlayer1,
                reportPlayer2,
                reportPlayer3
        ))

        // Assert
        verify(view).onClientConnected(10)
        verify(view).onClientConnected(11)
        verify(view).onClientConnected(12)
    }

    @Test
    fun oldPlayerExist_reportSameAsOld_noAction() {
        // Arrange
        val reportPlayer1 = ReportPlayer().apply {
            id = 10
            name = "test_name"
        }
        val existPlayer1: Player = mock()
        whenever(existPlayer1.id).thenReturn(10)

        var presenter = ClientPresenterImpl(
                false,
                view,
                interactor,
                listOf(existPlayer1)
        )

        // Act
        presenter.onPlayersUpdate(listOf(
                reportPlayer1
        ))

        // Assert
        verify(view, never()).onClientConnected(any())
        verify(view, never()).onClientDisconnected(any())
    }

    @Test
    fun oldPlayerExist_reportEmpty_removeOld() {
        // Arrange
        val existPlayer1: Player = mock()
        whenever(existPlayer1.id).thenReturn(10)

        var presenter = ClientPresenterImpl(
                false,
                view,
                interactor,
                listOf(existPlayer1)
        )

        // Act
        presenter.onPlayersUpdate(emptyList())

        // Assert
        verify(view).onClientDisconnected(10)
    }

    @Test
    fun oldPlayerExist_reportOtherNew_removeOldCreateNew() {
        // Arrange
        val reportPlayer1 = ReportPlayer().apply {
            id = 10
            name = "test_name"
        }
        val existPlayer1: Player = mock()
        whenever(existPlayer1.id).thenReturn(20)

        var presenter = ClientPresenterImpl(
                false,
                view,
                interactor,
                listOf(existPlayer1)
        )

        // Act
        presenter.onPlayersUpdate(listOf(reportPlayer1))

        // Assert
        verify(view).onClientConnected(10)
        verify(view).onClientDisconnected(20)
    }
}