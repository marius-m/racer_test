package lt.markmerkk.app.network.events

import lt.markmerkk.app.network.events.models.PlayerPosition

class EventPlayersPosition(
        var playersPosition: List<PlayerPosition> = emptyList()
) : NetworkEvent