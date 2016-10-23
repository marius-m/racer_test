package lt.markmerkk.app.mvp

import lt.markmerkk.app.network.events.NetworkEvent

interface ServerEventProvider {
    fun event(eventObject: NetworkEvent)
}