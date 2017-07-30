package lt.markmerkk.app.entities

/**
 * Represents player
 */
interface Player {
    val id: Int
    val name: String

    fun update(deltaTime: Float)

    fun destroy()
}