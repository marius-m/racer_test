package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-20
 */
interface WorldPresenter : Presenter {
    fun render(deltaTime: Float)
}