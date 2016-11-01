package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderServerImpl
import lt.markmerkk.app.mvp.interactors.ServerEventListener
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.ReportPlayer
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerPresenterImpl(
        private val isHost: Boolean,
        private val view: ServerView,
        private val serverInteractor: ServerInteractor,
        private val playerInteractor: PlayerInteractor,
        private val players: List<Player>
) : ServerPresenter, ServerEventListener {

    override fun onAttach() {
        if (!isHost) return
        serverInteractor.start(NetworkEventProviderServerImpl(this))
    }

    override fun onDetach() {
        if (!isHost) return
        serverInteractor.stop()
    }

    override fun update() {
    }

    //region Network events

    override fun onClientConnected(connectionId: Int) {
        val newPlayer = playerInteractor.createPlayer(connectionId)
        playerInteractor.addPlayer(newPlayer)
    }

    override fun onClientDisconnected(connectionId: Int) {
        playerInteractor.removePlayerByConnectionId(connectionId)
        sendPlayerUpdate(players)
    }

    override fun onClientHello() {
        sendPlayerUpdate(players)
    }

    //endregion

    fun sendPlayerUpdate(players: List<Player>) {
        if (players.size == 0) return
        val reportPlayers = players.map {
            ReportPlayer().apply {
                id = it.id
                name = it.name
            }
        }
        serverInteractor.sendPlayerUpdate(reportPlayers)
        logger.debug("Reporting player list: $reportPlayers")
    }

    companion object {
        val logger = LoggerFactory.getLogger(ServerPresenterImpl::class.java)
    }

}