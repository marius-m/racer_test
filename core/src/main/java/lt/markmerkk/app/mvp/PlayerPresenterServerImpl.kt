package lt.markmerkk.app.mvp

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.CarBox2DImpl
import lt.markmerkk.app.box2d.CarImpl
import lt.markmerkk.app.entities.PlayerServer
import lt.markmerkk.app.entities.PlayerServerImpl
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class PlayerPresenterServerImpl(
        private val world: World,
        private val players: MutableList<PlayerServer>
) : PlayerPresenterServer {

    private var playerCounter = 0

    override fun render(deltaTime: Float) {
        players.forEach {
            it.update(deltaTime)
        }
    }

    override fun createPlayerById(connectionId: Int) {
        playerCounter += 1
        val playerServerImpl = PlayerServerImpl(
                id = connectionId,
                name = "Player " + playerCounter,
                car = CarImpl(CarBox2DImpl(world, Vector2(2.0f, 5.0f)))
        )
        players.add(playerServerImpl)
    }

    override fun removePlayerByConnectionId(connectionId: Int) {
        val playerById = players.find { it.id == connectionId }
        if (playerById != null) {
            players.remove(playerById)
        }
    }

    override fun players(): List<PlayerServer> {
        return players
    }

    companion object {
        val logger = LoggerFactory.getLogger(PlayerPresenterServerImpl::class.java)!!
    }

}