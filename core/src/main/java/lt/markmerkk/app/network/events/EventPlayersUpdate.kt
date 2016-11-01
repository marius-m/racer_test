package lt.markmerkk.app.network.events

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 *
 * Event when server reports all the current clients to all the clients
 */
class EventPlayersUpdate : NetworkEvent {
    var reportPlayers: List<ReportPlayer> = emptyList()
}