package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
class ServerPresenterImpl(
        private val serverInteractor: ServerInteractor
) : ServerPresenter {


    init {
    }

    override fun onAttach() {
        serverInteractor.start()
    }

    override fun onDetach() {
        serverInteractor.stop()
    }

}