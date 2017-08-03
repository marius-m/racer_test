package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.Movement

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
interface ClientInteractor {
    fun start(eventProvider: NetworkEventProvider)
    fun stop()

    /**
     * Sends a [lt.markmerkk.app.entities.Movement] code
     */
    fun sendMovementEventCode(connectionId: Int, movementEvent: Movement)
}