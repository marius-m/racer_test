package lt.markmerkk.app.mvp

import com.badlogic.gdx.Input
import com.nhaarman.mockito_kotlin.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-21
 */
class InputPresenterImplHandleSteerTest {
    val carInputInteractor = mock<CarInputInteractor>()
    val input: Input = mock()
    val presenter = InputPresenterImpl(
            input,
            carInputInteractor
    )

    @Test
    fun noInput_steerNone() {
        // Arrange
        // Act
        presenter.handleSteer()

        // Assert
        verify(carInputInteractor, never()).steerLeft()
        verify(carInputInteractor, never()).steerRight()
        verify(carInputInteractor).steerNone()
    }

    @Test
    fun inputLeft_steerLeft() {
        // Arrange
        whenever(input.isKeyPressed(Input.Keys.LEFT)).thenReturn(true)

        // Act
        presenter.handleSteer()

        // Assert
        verify(carInputInteractor).steerLeft()
        verify(carInputInteractor, never()).steerRight()
        verify(carInputInteractor, never()).steerNone()
    }

    @Test
    fun inputRight_steerRight() {
        // Arrange
        whenever(input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true)

        // Act
        presenter.handleSteer()

        // Assert
        verify(carInputInteractor, never()).steerLeft()
        verify(carInputInteractor).steerRight()
        verify(carInputInteractor, never()).steerNone()
    }


}