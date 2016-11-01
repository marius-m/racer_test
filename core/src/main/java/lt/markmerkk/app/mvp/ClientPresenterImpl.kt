package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.mvp.interactors.ClientEventListener
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderClientImpl
import lt.markmerkk.app.network.events.ReportPlayer
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientPresenterImpl(
        private val isHost: Boolean,
        private val view: ClientView,
        private val clientInteractor: ClientInteractor,
        private val players: List<Player>
) : ClientPresenter, ClientEventListener {

    override fun onAttach() {
        if (isHost) return
        clientInteractor.start(NetworkEventProviderClientImpl(this))
    }

    override fun onDetach() {
        if (isHost) return
        clientInteractor.stop()
    }

    override fun update() {
    }

    //region Client events

    override fun onPlayersUpdate(reportPlayers: List<ReportPlayer>) {
        val currentPlayers = players

        //Adding new players
        for (reportPlayer in reportPlayers) {
            val alreadyExistingPlayer = currentPlayers.find { it.id == reportPlayer.id }
            if (alreadyExistingPlayer != null) continue
            view.onClientConnected(reportPlayer.id)
        }

        // Removing not connected players
        for (player in currentPlayers) {
            val playerExist = reportPlayers.find { it.id == player.id }
            if (playerExist != null) continue
            view.onClientDisconnected(player.id)
        }
    }

    override fun onConnected(connectionId: Int) {
        clientInteractor.sendHello()
    }

    override fun onDisconnected(connectionId: Int) {
    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ClientPresenterImpl::class.java)!!
    }

}