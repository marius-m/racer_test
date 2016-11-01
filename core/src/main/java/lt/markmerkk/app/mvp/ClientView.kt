package lt.markmerkk.app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-23
 */
interface ClientView {
    fun onClientConnected(id: Int)
    fun onClientDisconnected(id: Int)
}