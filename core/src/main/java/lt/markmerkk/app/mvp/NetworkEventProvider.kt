package lt.markmerkk.app.mvp

import lt.markmerkk.app.network.events.NetworkEvent

interface NetworkEventProvider {
    fun event(eventObject: NetworkEvent)
}