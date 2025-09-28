package ru.miet.softwaretestinglabs.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.miet.softwaretestinglabs.Calculator;
import ru.miet.softwaretestinglabs.CalculatorView;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorPresenterImplTests {

    @Mock
    private Calculator calculator;

    @Mock
    private CalculatorView view;

    @InjectMocks
    private CalculatorPresenterImpl presenter;

    @BeforeEach
    void setUp() {
        presenter = new CalculatorPresenterImpl(calculator, view);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 8",
            "0, 0, 0",
            "-5, 3, -2",
            "2.5, 3.4, 5.9"
    })
    void onPlusClicked_ValidNumbers_ShouldPrintResult(double a, double b, double expected) {
        // given
        when(view.getFirstArgumentAsString()).thenReturn(String.valueOf(a));
        when(view.getSecondArgumentAsString()).thenReturn(String.valueOf(b));
        when(calculator.sum(a, b)).thenReturn(expected);

        // when
        presenter.onPlusClicked();

        // then
        verify(view).printResult(expected);
        verify(view, never()).displayError(anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 2",
            "0, 0, 0",
            "-5, -3, -2",
            "2.5, 1.4, 1.1"
    })
    void onMinusClicked_ValidNumbers_ShouldPrintResult(double a, double b, double expected) {
        // given
        when(view.getFirstArgumentAsString()).thenReturn(String.valueOf(a));
        when(view.getSecondArgumentAsString()).thenReturn(String.valueOf(b));
        when(calculator.subtract(a, b)).thenReturn(expected);

        // when
        presenter.onMinusClicked();

        // then
        verify(view).printResult(expected);
        verify(view, never()).displayError(anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 6",
            "0, 5, 0",
            "-5, -3, 15",
            "2.5, 4, 10"
    })
    void onMultiplyClicked_ValidNumbers_ShouldPrintResult(double a, double b, double expected) {
        // given
        when(view.getFirstArgumentAsString()).thenReturn(String.valueOf(a));
        when(view.getSecondArgumentAsString()).thenReturn(String.valueOf(b));
        when(calculator.multiply(a, b)).thenReturn(expected);

        // when
        presenter.onMultiplyClicked();

        // then
        verify(view).printResult(expected);
        verify(view, never()).displayError(anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "10, 2, 5",
            "0, 5, 0",
            "-15, -3, 5",
            "1, 3, 0.33333333"
    })
    void onDivideClicked_ValidDenominator_ShouldPrintResult(double a, double b, double expected) {
        // given
        when(view.getFirstArgumentAsString()).thenReturn(String.valueOf(a));
        when(view.getSecondArgumentAsString()).thenReturn(String.valueOf(b));
        when(calculator.divide(a, b)).thenReturn(expected);

        // when
        presenter.onDivideClicked();

        // then
        verify(view).printResult(expected);
        verify(view, never()).displayError(anyString());
    }

    @Test
    void onDivideClicked_ZeroDenominator_ShouldDisplayError() {
        // given
        when(view.getFirstArgumentAsString()).thenReturn("10");
        when(view.getSecondArgumentAsString()).thenReturn("0");
        when(calculator.divide(10, 0)).thenThrow(new ArithmeticException("Division by zero"));

        // when
        presenter.onDivideClicked();

        // then
        verify(view).displayError("Division by zero");
        verify(view, never()).printResult(anyDouble());
    }

    @Test
    void onDivideClicked_DenominatorBelowThreshold_ShouldDisplayError() {
        // given
        when(view.getFirstArgumentAsString()).thenReturn("10");
        when(view.getSecondArgumentAsString()).thenReturn("0.000000001"); // 1e-9
        when(calculator.divide(10, 1e-9)).thenThrow(new ArithmeticException("|b| должен быть больше чем 10e-8"));

        // when
        presenter.onDivideClicked();

        // then
        verify(view).displayError("|b| должен быть больше чем 10e-8");
        verify(view, never()).printResult(anyDouble());
    }

    @Test
    void onPlusClicked_InvalidFirstArgument_ShouldDisplayError() {
        // given
        when(view.getFirstArgumentAsString()).thenReturn("abc");
        when(view.getSecondArgumentAsString()).thenReturn("5");

        // when
        presenter.onPlusClicked();

        // then
        verify(view).displayError(anyString());
        verify(view, never()).printResult(anyDouble());
    }

    @Test
    void onPlusClicked_InvalidSecondArgument_ShouldDisplayError() {
        // given
        when(view.getFirstArgumentAsString()).thenReturn("5");
        when(view.getSecondArgumentAsString()).thenReturn("xyz");

        // when
        presenter.onPlusClicked();

        // then
        verify(view).displayError(anyString());
        verify(view, never()).printResult(anyDouble());
    }

    @Test
    void onMinusClicked_InvalidArguments_ShouldDisplayError() {
        // given
        when(view.getFirstArgumentAsString()).thenReturn("invalid");
        when(view.getSecondArgumentAsString()).thenReturn("data");

        // when
        presenter.onMinusClicked();

        // then
        verify(view).displayError(anyString());
        verify(view, never()).printResult(anyDouble());
    }

    @Test
    void actionPerformed_PlusButton_ShouldCallOnPlusClicked() {
        // given
        java.awt.event.ActionEvent plusEvent =
                new java.awt.event.ActionEvent(this, 0, "+");

        // when
        presenter.actionPerformed(plusEvent);

        // then
        verify(view, atLeastOnce()).getFirstArgumentAsString();
        verify(view, atLeastOnce()).getSecondArgumentAsString();
    }

    @Test
    void constructor_WithCalculatorViewImpl_ShouldSetActionListener() {
        // given
        CalculatorViewImpl viewImpl = mock(CalculatorViewImpl.class);

        // when
        CalculatorPresenterImpl presenterImpl = new CalculatorPresenterImpl(calculator, viewImpl);

        // then
        verify(viewImpl).setActionListener(presenterImpl);
    }
}