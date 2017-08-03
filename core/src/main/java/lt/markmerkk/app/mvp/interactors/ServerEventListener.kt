package lt.markmerkk.app.mvp.interactors

import lt.markmerkk.app.entities.Movement

/**
 * Events sent by the client
 */
interface ServerEventListener {
    fun onClientConnected(connectionId: Int)
    fun onClientDisconnected(connectionId: Int)

    fun onClientMovementEvent(connectionId: Int, movement: Movement)
}