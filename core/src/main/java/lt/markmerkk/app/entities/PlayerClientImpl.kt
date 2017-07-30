package lt.markmerkk.app.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import lt.markmerkk.app.screens.GameScreen

class PlayerClientImpl(
        override val id: Int = -1,
        override val name: String
) : PlayerClient {

    private val texture: Texture = Texture(Gdx.files.internal("data/car_small.png"))
    private val sprite: Sprite = Sprite(texture)

    init {
        sprite.setSize(
                sprite.width / GameScreen.PIXELS_PER_METER,
                sprite.height / GameScreen.PIXELS_PER_METER
        )
    }

    override fun update(
            positionX: Float,
            positionY: Float,
            angle: Float
    ) {
        sprite.setPosition(positionX, positionY)
        sprite.rotation = angle
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }

    override fun destroy() { }
}