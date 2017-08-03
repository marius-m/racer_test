package lt.markmerkk.app.network.events.models

import lt.markmerkk.app.Const

class PlayerPosition(
        var connectionId: Int = Const.NO_CONNECTION_ID,
        var positionX: Float = 0.0f,
        var positionY: Float = 0.0f
)