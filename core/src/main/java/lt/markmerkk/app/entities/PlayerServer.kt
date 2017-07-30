package lt.markmerkk.app.entities

/**
 * Represent player on server side (physical box2d body, position)
 */
interface PlayerServer {
    val id: Int
    val name: String

    /**
     * Updates box 2d position
     */
    fun update(
            deltaTime: Float
    )

    /**
     * Destroys its physical body and releases memory
     */
    fun destroy()
}