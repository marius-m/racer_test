package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
interface ClientInteractor {
    fun start(eventProvider: NetworkEventProvider)
    fun stop()
    /**
     * Send an event to the server with a greet
     */
    fun sendHello()
}