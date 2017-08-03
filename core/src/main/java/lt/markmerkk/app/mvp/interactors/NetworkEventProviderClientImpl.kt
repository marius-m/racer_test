package lt.markmerkk.app.mvp.interactors

import com.badlogic.gdx.Gdx
import lt.markmerkk.app.mvp.NetworkEventProvider
import lt.markmerkk.app.network.events.EventPlayersPosition
import lt.markmerkk.app.network.events.EventPlayersRegister
import lt.markmerkk.app.network.events.NetworkEvent
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
class NetworkEventProviderClientImpl(
        private val listener: ClientEventListener
) : NetworkEventProvider {

    override fun event(eventObject: NetworkEvent) {
        Gdx.app.postRunnable {
            when (eventObject) {
                is EventPlayersRegister -> listener.onPlayersRegister(eventObject.registerPlayers)
                is EventPlayersPosition -> listener.onPlayersPosition(eventObject.playersPosition)
                else -> logger.debug("Undefined event received: $eventObject")
            }
        }
    }

    override fun connected(connectionId: Int) {
        Gdx.app.postRunnable {
            listener.onConnected(connectionId)
        }
    }

    override fun disconnected(connectionId: Int) {
        Gdx.app.postRunnable {
            listener.onDisconnected(connectionId)
        }
    }

    companion object {
        val logger = LoggerFactory.getLogger(NetworkEventProviderClientImpl::class.java)!!
    }

}