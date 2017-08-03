package lt.markmerkk.app.network.events

/**
 * Represent movement event.
 * More info on events codes [lt.markmerkk.app.entities.Movement]
 */
class EventPlayerMovement(
        var connectionId: Int = -1,
        var movementEventCode: Int = -1
) : NetworkEvent
