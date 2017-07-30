package lt.markmerkk.app.mvp.interactors

/**
 * Events sent by the client
 */
interface ServerEventListener {
    fun onClientConnected(connectionId: Int)
    fun onClientDisconnected(connectionId: Int)

    fun onForwardStart(connectionId: Int)
    fun onForwardStop(connectionId: Int)
    fun onLeftStart(connectionId: Int)
    fun onLeftStop(connectionId: Int)
    fun onRightStart(connectionId: Int)
    fun onRightStop(connectionId: Int)
}