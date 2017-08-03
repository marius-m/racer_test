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
        val width = sprite.width / GameScreen.PIXELS_PER_METER
        val height = sprite.height / GameScreen.PIXELS_PER_METER
        sprite.setSize(width, height)
        sprite.setOrigin(width / 2, height / 2)
    }

    override fun update(
            positionX: Float,
            positionY: Float,
            angle: Float
    ) {
        sprite.setPosition(
                positionX / GameScreen.PIXELS_PER_METER - sprite.width / 2,
                positionY / GameScreen.PIXELS_PER_METER - sprite.height / 2
        )
        sprite.rotation = angle
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }

    override fun destroy() { }
}