package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import javafx.application.Platform
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderServerImpl
import lt.markmerkk.app.mvp.interactors.ServerEventListener
import lt.markmerkk.app.network.events.EventPlayerPosition
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
        serverInteractor.start()
        serverInteractor.eventProvider = NetworkEventProviderServerImpl(this)
    }

    override fun onDetach() {
        if (!isHost) return
        serverInteractor.eventProvider = null
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

    override fun onNewClient(id: Int) {
    }

    override fun onClientConnected(connectionId: Int) {
        Gdx.app.postRunnable { view.onClientConnected(connectionId) }
    }

    override fun onClientDisconnected(connectionId: Int) {
        Gdx.app.postRunnable { view.onClientDisconnected(connectionId) }
    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ServerPresenterImpl::class.java)
    }

}