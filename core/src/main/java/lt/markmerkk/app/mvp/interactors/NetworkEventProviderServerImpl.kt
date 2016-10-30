package lt.markmerkk.app.mvp.interactors

import lt.markmerkk.app.mvp.NetworkEventProvider
import lt.markmerkk.app.network.events.EventRegister
import lt.markmerkk.app.network.events.NetworkEvent
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
class NetworkEventProviderServerImpl(
        private val listener: ServerEventListener
) : NetworkEventProvider {

    override fun event(eventObject: NetworkEvent) {
        when(eventObject) {
            is EventRegister -> listener.onNewClient(eventObject.toString())
            else -> logger.debug("Undefined event received: $eventObject")
        }
    }

    companion object {
        val logger = LoggerFactory.getLogger(NetworkEventProviderServerImpl::class.java)
    }

}