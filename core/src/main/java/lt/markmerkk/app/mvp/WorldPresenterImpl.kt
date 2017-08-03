package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-20
 */
class WorldPresenterImpl(
        private val worldInteractor: WorldInteractor
) : WorldPresenter {

    override fun onAttach() {
    }

    override fun onDetach() {
    }

    override fun render(deltaTime: Float) {
        worldInteractor.step(deltaTime)
    }
}