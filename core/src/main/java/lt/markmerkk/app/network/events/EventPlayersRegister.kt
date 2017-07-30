package lt.markmerkk.app.network.events

import lt.markmerkk.app.network.events.models.PlayerRegister
import lt.markmerkk.app.network.events.models.ReportPlayer

/**
 * Event when server reports all the current clients to all the clients
 */
class EventPlayersRegister(
        var registerPlayers: List<PlayerRegister> = emptyList()
) : NetworkEvent {
    constructor() : this(emptyList())
}