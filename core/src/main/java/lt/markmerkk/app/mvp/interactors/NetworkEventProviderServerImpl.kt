package lt.markmerkk.app.mvp.interactors

import lt.markmerkk.app.mvp.NetworkEventProvider
import lt.markmerkk.app.network.events.EventHello
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
            is EventHello -> listener.onClientHello()
            else -> logger.debug("Undefined event received: $eventObject")
        }
    }

    override fun connected(connectionId: Int) {
        listener.onClientConnected(connectionId)
    }

    override fun disconnected(connectionId: Int) {
        listener.onClientDisconnected(connectionId)
    }

    companion object {
        val logger = LoggerFactory.getLogger(NetworkEventProviderServerImpl::class.java)
    }

}