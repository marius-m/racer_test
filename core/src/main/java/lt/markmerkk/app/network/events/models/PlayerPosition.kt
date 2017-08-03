package lt.markmerkk.app.network.events.models

import lt.markmerkk.app.Const
import lt.markmerkk.app.entities.PlayerServer

class PlayerPosition(
        var connectionId: Int = Const.NO_CONNECTION_ID,
        var positionX: Float = 0.0f,
        var positionY: Float = 0.0f,
        var angle: Float = 0.0f
) {
    override fun toString(): String {
        return "PlayerPosition(connectionId=$connectionId, positionX=$positionX, positionY=$positionY, angle=$angle)"
    }


    companion object {
        fun fromPlayer(player: PlayerServer): PlayerPosition {
            return PlayerPosition(
                    player.id,
                    player.getPositionX(),
                    player.getPositionY(),
                    player.getAngle()
            )
        }
    }

}