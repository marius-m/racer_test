package lt.markmerkk.app.entities

/**
 * Represent player on server side (physical box2d body, position)
 */
interface PlayerServer {
    val id: Int
    val name: String

    /**
     * Returns position coordinate x in the world
     */
    fun getPositionX(): Float

    /**
     * Returns position coordinate y in the world
     */
    fun getPositionY(): Float

    /**
     * Returns angle that car is in, in the world
     */
    fun getAngle(): Float

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