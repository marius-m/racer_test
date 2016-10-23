package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ClientPresenterImpl(
        private val isHost: Boolean,
        private val clientInteractor: ClientInteractor
) : ClientPresenter {
    override fun onAttach() {
        if (!isHost) return
        clientInteractor.start()
    }

    override fun onDetach() {
        if (!isHost) return
        clientInteractor.stop()
    }
}