package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.mvp.interactors.ClientEventListener
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderClientImpl
import lt.markmerkk.app.network.events.EventPlayersUpdate
import lt.markmerkk.app.network.events.ReportPlayer
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientPresenterImpl(
        private val isHost: Boolean,
        private val clientInteractor: ClientInteractor,
        private val players: List<Player>
) : ClientPresenter, ClientEventListener {

    override fun onAttach() {
        if (isHost) return
        clientInteractor.start()
        clientInteractor.eventProvider = NetworkEventProviderClientImpl(this)
    }

    override fun onDetach() {
        if (isHost) return
        clientInteractor.eventProvider = null
        clientInteractor.stop()
    }

    override fun update() {
    }

    //region Client events

    override fun onPlayersUpdate(reportPlayers: List<ReportPlayer>) {
    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ClientPresenterImpl::class.java)!!
    }

}