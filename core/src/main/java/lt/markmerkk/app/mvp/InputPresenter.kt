package lt.markmerkk.app.mvp

/**
 * Translates raw input into car movement triggers through [CarInputInteractor]
 */
interface InputPresenter : Presenter {
    fun render()
}