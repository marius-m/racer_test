package lt.markmerkk.app.mvp.interactors

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
interface ClientEventListener {
    fun onPlayerUpdate(positionX: Float, positionY: Float, angle: Float)
}