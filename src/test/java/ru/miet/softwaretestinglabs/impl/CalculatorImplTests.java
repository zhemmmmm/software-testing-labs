package ru.miet.softwaretestinglabs.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorImplTests {

    private final CalculatorImpl calculator = new CalculatorImpl();

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "0, 0, 0", "-5, 3, -2", "2.5, 3.4, 5.9"})
    void sum_ShouldReturnCorrectResult(double a, double b, double expected) {
        // given

        // when
        double result = calculator.sum(a, b);

        // then
        assertEquals(expected, result, 1e-8);
    }

    @ParameterizedTest
    @CsvSource({"5, 3, 2", "0, 0, 0", "-5, -3, -2", "2.5, 1.4, 1.1"})
    void subtract_ShouldReturnCorrectResult(double a, double b, double expected) {
        // given

        // when
        double result = calculator.subtract(a, b);

        // then
        assertEquals(expected, result, 1e-8);
    }

    @ParameterizedTest
    @CsvSource({"2, 3, 6", "0, 5, 0", "-5, -3, 15", "2.5, 4, 10"})
    void multiply_ShouldReturnCorrectResult(double a, double b, double expected) {
        // given

        // when
        double result = calculator.multiply(a, b);

        // then
        assertEquals(expected, result, 1e-8);
    }

    @ParameterizedTest
    @CsvSource({"10, 2, 5", "0, 5, 0", "-15, -3, 5", "1, 3, 0.33333333"})
    void divide_ValidDenominator_ShouldReturnCorrectResult(double a, double b, double expected) {
        // given

        // when
        double result = calculator.divide(a, b);

        // then
        assertEquals(expected, result, 1e-8);
    }

    @Test
    void divide_ZeroDenominator_ShouldThrowArithmeticException() {
        // given
        double a = 10;
        double b = 0;

        // when & then
        assertThrows(ArithmeticException.class, () -> calculator.divide(a, b));
    }

    @Test
    void divide_DenominatorBelowThreshold_ShouldThrowArithmeticException() {
        // given
        double a = 10;
        double b = 1e-9;

        // when & then
        assertThrows(ArithmeticException.class, () -> calculator.divide(a, b));
    }
}