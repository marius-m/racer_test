package lt.markmerkk.app.entities

import lt.markmerkk.app.box2d.Car

class PlayerClientImpl(
        override val id: Int = -1,
        override val name: String
) : Player {

    override fun update(deltaTime: Float) {
//        val newPositionX = GameScreen.PIXELS_PER_METER * car.x - carSprite.width / 2
//        val newPositionY = GameScreen.PIXELS_PER_METER * car.y - carSprite.height / 2
//        val newAngle = Math.toDegrees(car.angle.toDouble()).toFloat()

//        if (newPositionX != carSprite.x ||
//                newPositionY != carSprite.y ||
//                carSprite.rotation != newAngle) {
//        }
//
//        carSprite.setPosition(newPositionX, newPositionY)
//        carSprite.rotation = newAngle
    }

    override fun destroy() {
    }
}