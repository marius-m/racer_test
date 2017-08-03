package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Movement
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderServerImpl
import lt.markmerkk.app.mvp.interactors.ServerEventListener
import lt.markmerkk.app.network.events.models.PlayerPosition
import lt.markmerkk.app.network.events.models.PlayerRegister
import org.slf4j.LoggerFactory
import rx.Observable
import rx.Subscription

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerPresenterImpl(
        private val serverInteractor: ServerInteractor,
        private val playerPresenterServer: PlayerPresenterServer
) : ServerPresenter {

    private val subscriptions = mutableListOf<Subscription>()

    override fun onAttach() {
        serverInteractor.start(NetworkEventProviderServerImpl(serverEventListener))
    }

    override fun onDetach() {
        serverInteractor.stop()
        subscriptions.forEach { it.unsubscribe() }
    }

    override fun update() {
        val playersPosition = playerPresenterServer.players()
                .map { PlayerPosition(
                        it.id,
                        it.getPositionX(),
                        it.getPositionY()
                ) }
        serverInteractor.sendPlayerPosition(playersPosition)
    }

    //region Listeners

    val serverEventListener: ServerEventListener = object : ServerEventListener {
        override fun onClientConnected(connectionId: Int) {
            Observable.just(connectionId)
                    .subscribe({
                        playerPresenterServer.createPlayerById(connectionId)
                        serverInteractor.sendPlayerRegister(
                                playerPresenterServer
                                        .players()
                                        .map { PlayerRegister(it.id, it.name) }
                        )
                    }, {
                        logger.error("Error creating client", it)
                    }).apply { subscriptions.add(this) }
        }

        override fun onClientDisconnected(connectionId: Int) {
            Observable.just(connectionId)
                    .subscribe({
                        playerPresenterServer.removePlayerByConnectionId(it)
                        serverInteractor.sendPlayerRegister(
                                playerPresenterServer
                                        .players()
                                        .map { PlayerRegister(it.id, it.name) }
                        )
                    }, {
                        logger.error("Error disconnecting client", it)
                    }).apply { subscriptions.add(this) }
        }

        override fun onClientMovementEvent(connectionId: Int, movement: Movement) {
            playerPresenterServer.movePlayerWithMovement(connectionId, movement)
        }

    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ServerPresenterImpl::class.java)
    }

}