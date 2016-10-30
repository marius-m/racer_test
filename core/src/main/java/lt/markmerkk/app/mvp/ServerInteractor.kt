package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
interface ServerInteractor {
    var eventProvider: NetworkEventProvider?

    fun start()
    fun stop()
}