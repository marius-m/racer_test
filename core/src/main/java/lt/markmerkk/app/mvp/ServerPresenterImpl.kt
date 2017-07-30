package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.PlayerServer
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderServerImpl
import lt.markmerkk.app.mvp.interactors.ServerEventListener
import lt.markmerkk.app.network.events.models.PlayerRegister
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
) : ServerPresenter {

    val subscriptions = mutableListOf<Subscription>()

    override fun onAttach() {
        serverInteractor.start(NetworkEventProviderServerImpl(serverEventListener))
    }

    override fun onDetach() {
        serverInteractor.stop()
        subscriptions.forEach { it.unsubscribe() }
    }

    override fun update() {

    }

    //region Listeners

    val serverEventListener: ServerEventListener = object : ServerEventListener {
        override fun onClientConnected(connectionId: Int) {
            Observable.just(connectionId)
                    .subscribeOn(ioScheduler)
                    .observeOn(uiScheduler)
                    .subscribe({
                        playerProvider.create(it)
                        val playersAsRegisters = playerProvider
                                .all()
                                .map { PlayerRegister(it.id, it.name) }
                        serverInteractor.sendPlayerRegister(playersAsRegisters)
                    }, {
                        logger.error("Error creating client", it)
                    }).apply { subscriptions.add(this) }
        }

        override fun onClientDisconnected(connectionId: Int) {
            Observable.just(connectionId)
                    .subscribe({
                        playerPresenter.removePlayerByConnectionId(it)
                    }, {
                        logger.error("Error disconnecting client", it)
                    }).apply { subscriptions.add(this) }
        }

        override fun onForwardStart(connectionId: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onForwardStop(connectionId: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLeftStart(connectionId: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLeftStop(connectionId: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onRightStart(connectionId: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onRightStop(connectionId: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ServerPresenterImpl::class.java)
    }

}