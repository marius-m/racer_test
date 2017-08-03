package lt.markmerkk.app.box2d

import com.badlogic.gdx.physics.box2d.World

interface WorldProvider {
    fun get(): World
}