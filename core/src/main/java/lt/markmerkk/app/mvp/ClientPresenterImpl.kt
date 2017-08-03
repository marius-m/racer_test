package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Movement
import lt.markmerkk.app.entities.PlayerClient
import lt.markmerkk.app.entities.PlayerClientImpl
import lt.markmerkk.app.mvp.interactors.ClientEventListener
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderClientImpl
import lt.markmerkk.app.network.events.models.PlayerPosition
import lt.markmerkk.app.network.events.models.PlayerRegister
import org.slf4j.LoggerFactory
import rx.Subscription

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientPresenterImpl(
        private val clientInteractor: ClientInteractor,
        private val players: MutableList<PlayerClient>
) : ClientPresenter {

    private var connectionId: Int = NO_ID
    private var subscription: Subscription? = null

    override fun onAttach() {
        clientInteractor.start(NetworkEventProviderClientImpl(clientEventListener))
    }

    override fun onDetach() {
        subscription?.unsubscribe()
        clientInteractor.stop()
    }

    override fun update() { }

    override fun updateInputMovement(movement: Movement) {
        clientInteractor.sendMovementEventCode(connectionId, movement)
    }

    //region Listeners

    val clientEventListener: ClientEventListener = object : ClientEventListener {
        override fun onConnected(connectionId: Int) {
            this@ClientPresenterImpl.connectionId = connectionId
        }

        override fun onDisconnected(connectionId: Int) {
            this@ClientPresenterImpl.connectionId = NO_ID
        }

        override fun onPlayersRegister(registeredPlayers: List<PlayerRegister>) {
            players.clear()
            players.addAll(
                    registeredPlayers.map {
                        PlayerClientImpl(
                                it.connectionId,
                                it.name
                        )
                    }
            )
        }

        override fun onPlayersPosition(playersPosition: List<PlayerPosition>) {
            for (positionFromRemote in playersPosition) {
                val localPlayer = players.find { it.id == positionFromRemote.connectionId }
                if (localPlayer == null) continue
                localPlayer.update(
                        positionFromRemote.positionX,
                        positionFromRemote.positionY,
                        0.0f
                )
            }
        }

    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ClientPresenterImpl::class.java)!!

        const val NO_ID = -1
    }

}