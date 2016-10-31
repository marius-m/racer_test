package lt.markmerkk.app.mvp.interactors

interface ServerEventListener {
    fun onNewClient(id: Int)
    fun onClientConnected(connectionId: Int)
    fun onClientDisconnected(connectionId: Int)
}