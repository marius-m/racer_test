package lt.markmerkk.app.mvp.interactors

interface ServerEventListener {
    fun onNewClient(clientName: String)
}