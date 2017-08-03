package lt.markmerkk.app.entities

/**
 * Defines car movement event
 */
enum class Movement(
        private val eventCode: Int = -1
) {
    UNKNOWN(-1),
    FORWARD_START(0),
    FORWARD_STOP(1),
    BACKWARD_START(2),
    BACKWARD_STOP(3),
    ;

    fun toCode(): Int = eventCode

    companion object {
        fun fromCode(eventCode: Int): Movement {
            when (eventCode) {
                0 -> return FORWARD_START
                1 -> return FORWARD_STOP
                2 -> return BACKWARD_START
                3 -> return BACKWARD_STOP
                else -> return UNKNOWN
            }
        }
    }

}