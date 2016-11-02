package lt.markmerkk.app.mvp

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.box2d.CarBridge
import lt.markmerkk.app.entities.Player
import org.junit.Assert.*
import org.junit.Before
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
    val fakePlayer1: Player = mock()
    val fakePlayer2: Player = mock()
    val fakePlayer3: Player = mock()
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

    @Before
    fun setUp() {
        whenever(fakePlayer1.id).thenReturn(1)
        whenever(fakePlayer1.name).thenReturn("test_name_1")
        whenever(fakePlayer2.id).thenReturn(2)
        whenever(fakePlayer2.name).thenReturn("test_name_2")
        whenever(fakePlayer3.id).thenReturn(3)
        whenever(fakePlayer3.name).thenReturn("test_name_3")
    }

    @Test
    fun noDirtyPlayers_noTrigger() {
        // Arrange
        whenever(fakePlayer1.dirty).thenReturn(false)
        whenever(fakePlayer2.dirty).thenReturn(false)
        whenever(fakePlayer3.dirty).thenReturn(false)

        // Act
        presenter.updatePosition(players)

        // Assert
        verify(serverInteractor, never()).sendPlayerUpdate(any())
    }

    @Test
    fun dirtyPlayer_updateAll() {
        // Arrange
        whenever(fakePlayer1.dirty).thenReturn(false)
        whenever(fakePlayer2.dirty).thenReturn(true)
        whenever(fakePlayer3.dirty).thenReturn(false)

        // Act
        presenter.updatePosition(players)

        // Assert
        verify(serverInteractor).sendPlayerUpdate(any())
    }
    @Test
    fun dirtyPlayer_resetNotDirty() {
        // Arrange
        whenever(fakePlayer1.dirty).thenReturn(false)
        whenever(fakePlayer2.dirty).thenReturn(true)
        whenever(fakePlayer3.dirty).thenReturn(false)

        // Act
        presenter.updatePosition(players)

        // Assert
        verify(fakePlayer1).dirty = false
        verify(fakePlayer2).dirty = false
        verify(fakePlayer3).dirty = false
    }
}