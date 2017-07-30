package lt.markmerkk.app.mvp.interactors

import lt.markmerkk.app.mvp.NetworkEventProvider
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
        when (eventObject) {
            is EventPlayersRegister -> listener.onPlayersRegister(eventObject.registerPlayers)
            else -> logger.debug("Undefined event received: $eventObject")
        }
    }

    override fun connected(connectionId: Int) {
        listener.onConnected(connectionId)
    }

    override fun disconnected(connectionId: Int) {
        listener.onDisconnected(connectionId)
    }

    companion object {
        val logger = LoggerFactory.getLogger(NetworkEventProviderClientImpl::class.java)!!
    }

}