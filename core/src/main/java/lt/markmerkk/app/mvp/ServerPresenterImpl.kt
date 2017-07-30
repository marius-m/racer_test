package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.PlayerServer
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderServerImpl
import lt.markmerkk.app.mvp.interactors.ServerEventListener
import org.slf4j.LoggerFactory
import rx.Observable
import rx.Scheduler
import rx.Subscription

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerPresenterImpl(
        private val serverInteractor: ServerInteractor,
        private val playerProvider: PlayerProvider<PlayerServer>,
        private val playerPresenter: PlayerPresenter<PlayerServer>,
        private val uiScheduler: Scheduler,
        private val ioScheduler: Scheduler
) : ServerPresenter, ServerEventListener {

    val subscriptions = mutableListOf<Subscription>()

    override fun onAttach() {
        serverInteractor.start(NetworkEventProviderServerImpl(this))
    }

    override fun onDetach() {
        serverInteractor.stop()
        subscriptions.forEach { it.unsubscribe() }
    }

    override fun update() {
//        updatePosition(players)
    }

    //region Network events

    override fun onClientConnected(connectionId: Int) {
        Observable.just(connectionId)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe({
                    playerPresenter.addPlayer(
                            playerProvider.create(it)
                    )
                }, {
                    logger.error("Error creating client", it)
                }).apply { subscriptions.add(this) }
    }

    override fun onClientDisconnected(connectionId: Int) {
        // Will not work with threading targets ??
        Observable.just(connectionId)
                .subscribe({
                    playerPresenter.removePlayerByConnectionId(it)
                }, {
                    logger.error("Error disconnecting client", it)
                }).apply { subscriptions.add(this) }
    }

    override fun onClientHello() {
//        sendPlayerUpdate(players)
    }

    //endregion

    /**
     * Updates position whenever its needed
     */
    fun updatePosition(players: List<PlayerServer>) {
//        val playersDirty = players.find { it.dirty == true } != null
//        if (playersDirty) {
//            logger.info("Found dirty players")
//            serverInteractor.sendPositionUpdate()
//            players.forEach { it.dirty = false }
//        }
    }

    fun sendPlayerUpdate(players: List<PlayerServer>) {
//        if (players.size == 0) return
//        val reportPlayers = players.map {
//            ReportPlayer().apply {
//                id = it.id
//                name = it.name
//            }
//        }
//        serverInteractor.sendPlayerUpdate(reportPlayers)
    }

    companion object {
        val logger = LoggerFactory.getLogger(ServerPresenterImpl::class.java)
    }

}