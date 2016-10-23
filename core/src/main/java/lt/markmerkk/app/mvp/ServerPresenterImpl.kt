package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerPresenterImpl(
        private val isHost: Boolean,
        private val serverInteractor: ServerInteractor
) : ServerPresenter {

    override fun onAttach() {
        if (!isHost) return
        serverInteractor.start()
    }

    override fun onDetach() {
        if (!isHost) return
        serverInteractor.stop()
    }

}