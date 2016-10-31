package lt.markmerkk.app.network.events

/**
 * @author mariusmerkevicius
 * @since 2016-10-31
 */
class EventPlayersUpdate : NetworkEvent {
    var reportPlayers: List<ReportPlayer> = emptyList()
}