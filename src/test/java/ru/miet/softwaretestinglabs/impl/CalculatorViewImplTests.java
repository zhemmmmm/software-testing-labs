package ru.miet.softwaretestinglabs.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorViewImplTests {

    private final CalculatorViewImpl view = new CalculatorViewImpl();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Test
    void printResult_ShouldOutputToSystemOut() {
        // given
        System.setOut(new PrintStream(outContent));
        double result = 5.5;

        // when
        view.printResult(result);

        // then
        assertEquals("5.5" + System.lineSeparator(), outContent.toString());
        System.setOut(System.out);
    }

    @Test
    void displayError_ShouldOutputToSystemErr() {
        // given
        System.setErr(new PrintStream(errContent));
        String message = "Test error";

        // when
        view.displayError(message);

        // then
        assertEquals("Test error" + System.lineSeparator(), errContent.toString());
        System.setErr(System.err);
    }

    @ParameterizedTest
    @CsvSource({"5 3, 5", "10.5 2.2, 10.5", "-5 3, -5"})
    void getFirstArgumentAsString_ShouldReturnFirstPart(String input, String expected) {
        // given

        // when
        String result = view.getFirstArgumentAsString(input);

        // then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"5 3, 3", "10.5 2.2, 2.2", "-5 3, 3"})
    void getSecondArgumentAsString_ShouldReturnSecondPart(String input, String expected) {
        // given

        // when
        String result = view.getSecondArgumentAsString(input);

        // then
        assertEquals(expected, result);
    }
}