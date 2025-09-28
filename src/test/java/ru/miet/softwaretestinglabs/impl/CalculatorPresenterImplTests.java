package ru.miet.softwaretestinglabs.impl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Test
    void onPlusClicked_ValidArguments_ShouldPrintResult() {
        // given
        String request = "5 3";
        when(view.getFirstArgumentAsString(request)).thenReturn("5");
        when(view.getSecondArgumentAsString(request)).thenReturn("3");
        when(calculator.sum(5, 3)).thenReturn(8.0);

        // when
        presenter.onPlusClicked(request);

        // then
        verify(view).printResult(8.0);
        verify(view, never()).displayError(any());
    }

    @Test
    void onMinusClicked_InvalidArguments_ShouldDisplayError() {
        // given
        String request = "5 invalid";
        when(view.getFirstArgumentAsString(request)).thenReturn("5");
        when(view.getSecondArgumentAsString(request)).thenReturn("invalid");

        // when
        presenter.onMinusClicked(request);

        // then
        verify(view).displayError(any());
        verify(view, never()).printResult(anyDouble());
    }

    @Test
    void onDivideClicked_DivisionByZero_ShouldDisplayError() {
        // given
        String request = "10 0";
        when(view.getFirstArgumentAsString(request)).thenReturn("10");
        when(view.getSecondArgumentAsString(request)).thenReturn("0");
        when(calculator.divide(10, 0)).thenThrow(new ArithmeticException());

        // when
        presenter.onDivideClicked(request);

        // then
        verify(view).displayError(any());
        verify(view, never()).printResult(anyDouble());
    }
}