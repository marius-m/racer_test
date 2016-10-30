package lt.markmerkk.app.mvp.interactors

import lt.markmerkk.app.mvp.NetworkEventProvider
import lt.markmerkk.app.network.events.EventPlayerPosition
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
            is EventPlayerPosition -> listener.onPlayerUpdate(eventObject.positionX, eventObject.positionY)
            else -> logger.debug("Undefined event received: $eventObject")
        }
    }

    companion object {
        val logger = LoggerFactory.getLogger(NetworkEventProviderClientImpl::class.java)!!
    }

}