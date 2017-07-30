package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.PlayerClient
import lt.markmerkk.app.mvp.interactors.ClientEventListener
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderClientImpl
import lt.markmerkk.app.network.events.models.PlayerRegister
import lt.markmerkk.app.network.events.models.ReportPlayer
import org.slf4j.LoggerFactory
import rx.Scheduler
import rx.Subscription

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientPresenterImpl(
        private val clientInteractor: ClientInteractor,
        private val playerPresenter: PlayerPresenter<PlayerClient>,
        private val players: List<PlayerClient>,
        private val uiScheduler: Scheduler,
        private val ioScheduler: Scheduler
) : ClientPresenter {

    var subscription: Subscription? = null

    override fun onAttach() {
        clientInteractor.start(NetworkEventProviderClientImpl(clientEventListener))
    }

    override fun onDetach() {
        subscription?.unsubscribe()
        clientInteractor.stop()
    }

    override fun update() { }


    //region Listeners

    private val clientEventListener: ClientEventListener = object : ClientEventListener {
        override fun onConnected(connectionId: Int) { }

        override fun onDisconnected(connectionId: Int) { }

        override fun onPlayersRegister(registeredPlayers: List<PlayerRegister>) {
            logger.debug("onPlayersRegister(registeredPlayers: List<PlayerRegister>)")
        }

    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ClientPresenterImpl::class.java)!!
    }

}