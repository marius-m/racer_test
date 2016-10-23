package lt.markmerkk.app.mvp

import lt.markmerkk.app.Const
import lt.markmerkk.app.network.GameClient
import java.net.InetAddress

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientInteractorImpl : ClientInteractor {

    private val client = GameClient()

    override fun start() {
        client.start()
        client.connect(
                Const.CONN_TIMEOUT_MILLIS,
                InetAddress.getLocalHost(),
                Const.PORT_TCP,
                Const.PORT_UDP
        )
    }

    override fun stop() {
        client.stop()
    }
}