package lt.markmerkk.app.entities

/**
 * Represent player on server side (physical box2d body, position)
 */
interface PlayerServer {
    val id: Int
    val name: String

    fun getPositionX(): Float

    fun getPositionY(): Float

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

    /**
     * Updates player movement
     */
    fun updateMovement(movement: Movement)

}