package lt.markmerkk.app.mvp

import com.badlogic.gdx.Input
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-22
 */
class InputPresenterBox2dImplTest {
    val carInputInteractor = mock<CarInputInteractor>()
    val input: Input = mock()

    @Test
    fun interactorValid_triggerEvents() {
        // Arrange
        val presenter = InputPresenterBox2dImpl(input)
        presenter.carInputInteractor = this.carInputInteractor

        // Act
        presenter.render()

        // Assert
        verify(carInputInteractor).steerNone()
        verify(carInputInteractor).moveNone()
    }

    @Test
    fun carInputValid_steerNone() {
        // Arrange
        val presenter = InputPresenterBox2dImpl(input)

        // Act
        presenter.render()

        // Assert
        verify(carInputInteractor, never()).steerNone()
        verify(carInputInteractor, never()).moveNone()
    }
}