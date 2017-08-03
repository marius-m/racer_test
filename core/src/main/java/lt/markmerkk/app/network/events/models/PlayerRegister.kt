package lt.markmerkk.app.network.events.models

/**
 * Event when sending registered players on the server
 */
class PlayerRegister(
        var connectionId: Int = -1,
        var name: String = ""
)