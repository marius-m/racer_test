package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Movement

interface ClientPresenter : Presenter {
    fun update()

    /**
     * Updates client movement
     */
    fun updateInputMovement(movement: Movement)
}