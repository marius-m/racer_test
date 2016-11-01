package lt.markmerkk.app.mvp.interactors

interface ServerEventListener {
    fun onClientHello()
    fun onClientConnected(connectionId: Int)
    fun onClientDisconnected(connectionId: Int)
}