package lt.markmerkk.app.mvp

import lt.markmerkk.app.Const
import lt.markmerkk.app.network.GameServer

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerInteractorImpl : ServerInteractor {

    private var server: GameServer? = null

    override fun start() {
        server = GameServer().apply {
            bind(Const.TCP_PORT)
            start()
        }
    }

    override fun stop() {
        server?.stop()
    }
}