package lt.markmerkk.app.mvp

import lt.markmerkk.app.network.events.NetworkEvent

interface NetworkEventProvider {
    fun connected(connectionId: Int)
    fun disconnected(connectionId: Int)
    fun event(eventObject: NetworkEvent)
}