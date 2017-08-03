package lt.markmerkk.app.mvp

import com.badlogic.gdx.physics.box2d.World
import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.box2d.WorldProvider
import lt.markmerkk.app.entities.Movement
import lt.markmerkk.app.entities.PlayerServer
import lt.markmerkk.app.entities.PlayerServerImpl
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author mariusmerkevicius
 * *
 * @since 2017-08-03
 */
class PlayerPresenterServerImplMovePlayerWithMovementTest {

    @Mock lateinit var worldProvider: WorldProvider
    lateinit var playerPresenter: PlayerPresenterServer

    val players = mutableListOf<PlayerServer>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        playerPresenter = PlayerPresenterServerImpl(
                worldProvider,
                players
        )
    }

    @Test
    fun validPlayer_updateMovement() {
        val validPlayerId = 1
        val player = createPlayerWithId(validPlayerId)
        players.add(player)

        playerPresenter.movePlayerWithMovement(validPlayerId, Movement.FORWARD_START)

        verify(player).updateMovement(any())
    }

    @Test
    fun noSuchPlayer_ignore() {
        val validPlayerId = 1
        val invalidPlayerId = 2
        val player = createPlayerWithId(validPlayerId)
        players.add(player)

        playerPresenter.movePlayerWithMovement(invalidPlayerId, Movement.FORWARD_START)

        verify(player, never()).updateMovement(any())
    }

    fun createPlayerWithId(id: Int): PlayerServer {
        val player: PlayerServer = mock()
        doReturn(id).whenever(player).id
        return player
    }

}