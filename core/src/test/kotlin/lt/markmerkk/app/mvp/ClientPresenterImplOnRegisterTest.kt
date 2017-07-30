package lt.markmerkk.app.mvp

import lt.markmerkk.app.entities.PlayerClient
import lt.markmerkk.app.network.events.models.PlayerRegister
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2017-07-30
 */
class ClientPresenterImplOnRegisterTest {

    @Mock lateinit var clientInteractor: ClientInteractor
    lateinit var presenter: ClientPresenterImpl

    val players = mutableListOf<PlayerClient>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ClientPresenterImpl(
                clientInteractor,
                players,
                Schedulers.immediate(),
                Schedulers.immediate()
        )
    }

    @Test
    fun validPlayerRegister() {
        presenter.clientEventListener.onPlayersRegister(
                listOf(
                        PlayerRegister(1, "valid_player")
                )
        )

        assertEquals(1, players.size)
    }
}