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
        if (players.size == 0) return
//        val updateEvent = EventPlayerPosition()
//        updateEvent.positionX = spriteBundleInteractor.first().sprite.x
//        updateEvent.positionY = spriteBundleInteractor.first().sprite.y
//        updateEvent.angle = spriteBundleInteractor.first().sprite.rotation
//        serverInteractor.server?.sendToAllUDP(updateEvent)
    }

    //region Network events

    override fun onClientConnected(connectionId: Int) {
        view.onClientConnected(connectionId)
//        sendPlayerUpdate(players)
    }

    override fun onClientDisconnected(connectionId: Int) {
        view.onClientDisconnected(connectionId)
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
                x = it.carBridge.x
                y = it.carBridge.y
            }
        }
        serverInteractor.sendPlayerUpdate(reportPlayers)
    }

    companion object {
        val logger = LoggerFactory.getLogger(ServerPresenterImpl::class.java)
    }

}