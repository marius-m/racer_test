package lt.markmerkk.app.mvp

import com.badlogic.gdx.Gdx
import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.mvp.interactors.ClientEventListener
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderClientImpl
import lt.markmerkk.app.network.events.ReportPlayer
import org.slf4j.LoggerFactory
import rx.Observable
import rx.Scheduler

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientPresenterImpl(
        private val isHost: Boolean,
        private val view: ClientView,
        private val clientInteractor: ClientInteractor,
        private val playerInteractor: PlayerInteractor,
        private val players: List<Player>,
        private val uiScheduler: Scheduler,
        private val ioScheduler: Scheduler
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

    // todo : Rewrite this in RX for threading fix (to be actually testable)
    override fun onPlayersUpdate(reportPlayers: List<ReportPlayer>) {
        val currentPlayers = players

        //Adding new players
        for (reportPlayer in reportPlayers) {
            val alreadyExistingPlayer = currentPlayers.find { it.id == reportPlayer.id }
            if (alreadyExistingPlayer != null) continue
            Gdx.app.postRunnable {
                val newPlayer = playerInteractor.createPlayer(reportPlayer.id)
                playerInteractor.addPlayer(newPlayer)
            }
        }

        // Removing not connected players
        for (player in currentPlayers) {
            val playerExist = reportPlayers.find { it.id == player.id }
            if (playerExist != null) continue
            Gdx.app.postRunnable {
                playerInteractor.removePlayerByConnectionId(player.id)
            }
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