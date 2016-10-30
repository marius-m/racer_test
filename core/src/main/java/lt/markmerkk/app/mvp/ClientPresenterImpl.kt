package lt.markmerkk.app.mvp

import lt.markmerkk.app.mvp.interactors.ClientEventListener
import lt.markmerkk.app.mvp.interactors.NetworkEventProviderClientImpl
import lt.markmerkk.app.mvp.painter.SpriteBundleInteractor
import org.slf4j.LoggerFactory

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientPresenterImpl(
        private val isHost: Boolean,
        private val clientInteractor: ClientInteractor,
        private val spriteBundleInteractors: List<SpriteBundleInteractor>
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

    override fun onPlayerUpdate(positionX: Float, positionY: Float, angle: Float) {
        logger.debug("Updating object position to $positionX x $positionY")
        if (spriteBundleInteractors.size == 0) return
        spriteBundleInteractors.first().updatePosition(positionX, positionY)
        spriteBundleInteractors.first().updateAngle(angle)
    }

    //endregion

    companion object {
        val logger = LoggerFactory.getLogger(ClientPresenterImpl::class.java)!!
    }

}