package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Player
import lt.markmerkk.app.mvp.interactors.ClientEventListener
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderClientImpl
import lt.markmerkk.app.network.events.ReportPlayer
import org.slf4j.LoggerFactory
import rx.Observable
import rx.Scheduler
import rx.Subscription

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

    var subscription: Subscription? = null

    override fun onAttach() {
        if (isHost) return
        clientInteractor.start(NetworkEventProviderClientImpl(this))
    }

    override fun onDetach() {
        subscription?.unsubscribe()
        if (isHost) return
        clientInteractor.stop()
    }

    override fun update() {
    }

    //region Client events

    override fun onPlayersUpdate(reportPlayers: List<ReportPlayer>) {
        subscription?.unsubscribe()
        val currentPlayers = players
        val newPlayersFilterObservable = Observable.from(reportPlayers)
                .subscribeOn(ioScheduler)
                .filter {
                    val reportPlayerId = it.id
                    currentPlayers.find { it.id == reportPlayerId } == null
                }
                .observeOn(uiScheduler)
                .doOnNext {
                    val newPlayer = playerInteractor.createPlayer(it.id)
                    playerInteractor.addPlayer(newPlayer)
                }
        val oldPlayersFilterObservable = Observable.from(currentPlayers)
                .subscribeOn(ioScheduler)
                .filter {
                    val currentPlayer = it
                    reportPlayers.find { currentPlayer.id == it.id } == null
                }
                .observeOn(uiScheduler)
                .doOnNext {
                    playerInteractor.removePlayerByConnectionId(it.id)
                }
        subscription = Observable.merge(newPlayersFilterObservable, oldPlayersFilterObservable)
                .subscribe({
                    logger.info("Player update complete!")
                })
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