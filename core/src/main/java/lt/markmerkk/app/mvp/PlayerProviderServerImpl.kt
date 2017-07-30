package lt.markmerkk.app.mvp

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import lt.markmerkk.app.box2d.CarBox2DImpl
import lt.markmerkk.app.box2d.CarImpl
import lt.markmerkk.app.entities.PlayerServer
import lt.markmerkk.app.entities.PlayerServerImpl

/**
 * Creates a [PlayerServer] for a server impl
 */
class PlayerProviderServerImpl(
        private val world: World,
        private val players: MutableList<PlayerServer>
) : PlayerProvider<PlayerServer> {

    private var playerCounter = 0

    override fun create(
            connectionId: Int
    ): PlayerServer {
        playerCounter += 1
        val playerServerImpl = PlayerServerImpl(
                id = connectionId,
                name = "Player " + playerCounter,
                car = CarImpl(CarBox2DImpl(world, Vector2(2.0f, 5.0f)))
        )
        players.add(playerServerImpl)
        return playerServerImpl
    }

    override fun all(): List<PlayerServer> = players

}