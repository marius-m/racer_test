package lt.markmerkk.app.mvp

import com.badlogic.gdx.Input
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-22
 */
class InputPresenterImplTest {
    val carInputInteractor = mock<CarInputInteractor>()
    val input: Input = mock()

    @Test
    fun interactorValid_triggerEvents() {
        // Arrange
        val presenter = InputPresenterImpl(
                input,
                carInputInteractor
        )

        // Act
        presenter.render()

        // Assert
        verify(carInputInteractor).steerNone()
        verify(carInputInteractor).moveNone()
    }

    @Test
    fun carInputValid_steerNone() {
        // Arrange
        val presenter = InputPresenterImpl(
                input,
                null
        )

        // Act
        presenter.render()

        // Assert
        verify(carInputInteractor, never()).steerNone()
        verify(carInputInteractor, never()).moveNone()
    }
}