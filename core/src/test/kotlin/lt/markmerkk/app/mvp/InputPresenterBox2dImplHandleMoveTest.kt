package lt.markmerkk.app.mvp

import com.badlogic.gdx.Input
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-22
 */
class InputPresenterBox2dImplHandleMoveTest {
    val carInputInteractor = mock<CarInputInteractor>()
    val input: Input = mock()
    val presenter = InputPresenterBox2dImpl(input).apply {
        carInputInteractor = this@InputPresenterBox2dImplHandleMoveTest.carInputInteractor
    }

    @Test
    fun noInput_steerNone() {
        // Arrange
        // Act
        presenter.handleMove()

        // Assert
        verify(carInputInteractor, never()).moveForward()
        verify(carInputInteractor, never()).moveBackward()
        verify(carInputInteractor).moveNone()
    }

    @Test
    fun up_moveForward() {
        // Arrange
        whenever(input.isKeyPressed(Input.Keys.UP)).thenReturn(true)

        // Act
        presenter.handleMove()

        // Assert
        verify(carInputInteractor).moveForward()
        verify(carInputInteractor, never()).moveBackward()
        verify(carInputInteractor, never()).moveNone()
    }

    @Test
    fun back_moveBackward() {
        // Arrange
        whenever(input.isKeyPressed(Input.Keys.DOWN)).thenReturn(true)

        // Act
        presenter.handleMove()

        // Assert
        verify(carInputInteractor, never()).moveForward()
        verify(carInputInteractor).moveBackward()
        verify(carInputInteractor, never()).moveNone()
    }

}