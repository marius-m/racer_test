package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.entities.PlayerServerImpl
import org.junit.Assert.*
import org.junit.Test
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-11-02
 */
class ServerPresenterImplUpdatePositionTest {
    val view: ServerView = mock()
    val eventProvider: NetworkEventProvider = mock()
    val playerInteractor: PlayerInteractor = mock()
    val serverInteractor: ServerInteractor = mock()
    val fakePlayer1 = PlayerServerImpl(1, "test_1", mock(), mock())
    val fakePlayer2 = PlayerServerImpl(1, "test_1", mock(), mock())
    val fakePlayer3 = PlayerServerImpl(1, "test_1", mock(), mock())
    val players = listOf(fakePlayer1, fakePlayer2, fakePlayer3)
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
    fun noDirtyPlayers_noTrigger() {
        // Arrange
        fakePlayer1.dirty = false
        fakePlayer2.dirty = false
        fakePlayer3.dirty = false

        // Act
        presenter.updatePosition(players)

        // Assert
        verify(serverInteractor, never()).sendPositionUpdate()
    }

    @Test
    fun dirtyPlayer_updateAll() {
        // Arrange
        fakePlayer1.dirty = false
        fakePlayer2.dirty = true
        fakePlayer3.dirty = false

        // Act
        presenter.updatePosition(players)

        // Assert
        verify(serverInteractor).sendPositionUpdate()
    }
    @Test
    fun dirtyPlayer_resetNotDirty() {
        // Arrange
        fakePlayer1.dirty = false
        fakePlayer2.dirty = true
        fakePlayer3.dirty = false

        // Act
        presenter.updatePosition(players)

        // Assert
        assertEquals(false, fakePlayer1.dirty)
        assertEquals(false, fakePlayer2.dirty)
        assertEquals(false, fakePlayer3.dirty)
    }
}