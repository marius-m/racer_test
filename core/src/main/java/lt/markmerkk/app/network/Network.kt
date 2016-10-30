package lt.markmerkk.app.network

import com.esotericsoftware.kryonet.EndPoint
import lt.markmerkk.app.network.events.EventRegister

/**
 * @author mariusmerkevicius
 * @since 2016-10-30
 */
object Network {
    fun register(endPoint: EndPoint) {
        val kryo = endPoint.kryo
        kryo.register(EventRegister::class.java)
    }
}