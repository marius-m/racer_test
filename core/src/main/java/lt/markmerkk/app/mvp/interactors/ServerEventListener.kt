package lt.markmerkk.app.mvp.interactors

interface ServerEventListener {
    fun onClientConnected(connectionId: Int)
    fun onClientDisconnected(connectionId: Int)
}