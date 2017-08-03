package lt.markmerkk.app.entities

import com.nhaarman.mockito_kotlin.*
import lt.markmerkk.app.box2d.Car
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author mariusmerkevicius
 * *
 * @since 2017-08-03
 */
class PlayerServerImplUpdateMovementTest {

    @Mock lateinit var car: Car
    lateinit var player: PlayerServerImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        player = PlayerServerImpl(
                1,
                "valid_player",
                car
        )
    }

    @Test
    fun validAcc_forward() {
        player.updateMovement(Movement.FORWARD_START)

        verify(car).accForward()
    }

    @Test
    fun validAcc_forward_stop() {
        player.updateMovement(Movement.FORWARD_START)
        player.updateMovement(Movement.FORWARD_STOP)

        verify(car).accForward()
        verify(car).accStop()
    }

    @Test
    fun validAcc_forw_back_stop() {
        player.updateMovement(Movement.FORWARD_START)
        player.updateMovement(Movement.FORWARD_STOP)
        player.updateMovement(Movement.BACKWARD_START)
        player.updateMovement(Movement.BACKWARD_STOP)

        verify(car).accForward()
        verify(car).accBackward()
        verify(car, atLeastOnce()).accStop()
    }

    @Test
    fun validSteer_left() {
        player.updateMovement(Movement.LEFT_START)
        player.updateMovement(Movement.LEFT_STOP)

        verify(car).steerLeft()
        verify(car).steerNone()
    }

    @Test
    fun validSteer_left_right() {
        player.updateMovement(Movement.LEFT_START)
        player.updateMovement(Movement.LEFT_STOP)
        player.updateMovement(Movement.RIGHT_START)
        player.updateMovement(Movement.RIGHT_STOP)

        verify(car).steerLeft()
        verify(car).steerRight()
        verify(car, atLeastOnce()).steerNone()
    }

    @Test
    fun valid_accForward_steerLeft() {
        player.updateMovement(Movement.FORWARD_START)
        player.updateMovement(Movement.LEFT_START)

        verify(car).accForward()
        verify(car).steerLeft()
        verify(car, never()).accStop()
        verify(car, never()).steerNone()
    }

    @Test
    fun invalid_unknown() {
        player.updateMovement(Movement.UNKNOWN)

        verify(car).accStop()
        verify(car).steerNone()
    }
}