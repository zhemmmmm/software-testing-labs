package ru.miet.softwaretestinglabs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.miet.softwaretestinglabs.Calculator;
import ru.miet.softwaretestinglabs.impl.CalculatorImpl;
import ru.miet.softwaretestinglabs.impl.CalculatorPresenterImpl;
import ru.miet.softwaretestinglabs.impl.CalculatorViewImpl;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculatorFullTests {

    private Calculator calculator;
    private CalculatorViewImpl view;
    private CalculatorPresenterImpl presenter;

    @BeforeEach
    void setUp() {
        calculator = new CalculatorImpl();
        view = new CalculatorViewImpl();
        presenter = new CalculatorPresenterImpl(calculator, view);
    }

    @Test
    void e2eTest_CompleteCalculationFlow_ShouldWorkCorrectly() {
        // Тест полного потока: ввод -> операция -> вывод

        // 1. Ввод данных
        simulateUserInput("firstArgumentField", "15");
        simulateUserInput("secondArgumentField", "3");

        // 2. Выполнение операции сложения
        simulateButtonClick("plusButton");

        // 3. Проверка результата
        String result = getResultFieldValue();
        assertEquals("18.0", result, "15 + 3 should equal 18");

        // 4. Выполнение операции вычитания
        simulateButtonClick("minusButton");
        result = getResultFieldValue();
        assertEquals("12.0", result, "15 - 3 should equal 12");

        // 5. Выполнение операции умножения
        simulateButtonClick("multiplyButton");
        result = getResultFieldValue();
        assertEquals("45.0", result, "15 * 3 should equal 45");

        // 6. Выполнение операции деления
        simulateButtonClick("divideButton");
        result = getResultFieldValue();
        assertEquals("5.0", result, "15 / 3 should equal 5");
    }

    @Test
    void e2eTest_ErrorHandling_ShouldDisplayAppropriateMessages() {
        // Тест обработки ошибок

        // 1. Деление на ноль
        simulateUserInput("firstArgumentField", "10");
        simulateUserInput("secondArgumentField", "0");
        simulateButtonClick("divideButton");

        // Проверяем, что результат не установлен (ошибка обработана)
        String result = getResultFieldValue();
        assertTrue(result.isEmpty() || result.equals("0.0") || result.equals("0"),
                "Division by zero should not set result field");

        // 2. Некорректный ввод
        simulateUserInput("firstArgumentField", "abc");
        simulateUserInput("secondArgumentField", "123");
        simulateButtonClick("plusButton");

        result = getResultFieldValue();
        assertTrue(result.isEmpty() || result.equals("0.0") || result.equals("0"),
                "Invalid input should not set result field");
    }

    @Test
    void e2eTest_ComplexCalculations_ShouldHandleVariousScenarios() {
        // Тест сложных сценариев вычислений

        // Дробные числа
        simulateUserInput("firstArgumentField", "3.14");
        simulateUserInput("secondArgumentField", "2.0");
        simulateButtonClick("multiplyButton");
        assertEquals("6.28", getResultFieldValue(), "3.14 * 2.0 should equal 6.28");

        // Отрицательные числа
        simulateUserInput("firstArgumentField", "-5");
        simulateUserInput("secondArgumentField", "3");
        simulateButtonClick("plusButton");
        assertEquals("-2.0", getResultFieldValue(), "-5 + 3 should equal -2");

        // Большие числа
        simulateUserInput("firstArgumentField", "999999");
        simulateUserInput("secondArgumentField", "1");
        simulateButtonClick("plusButton");
        assertEquals("1000000.0", getResultFieldValue(), "999999 + 1 should equal 1000000");
    }

    @Test
    void e2eTest_UIStateConsistency_ShouldMaintainCorrectState() {
        // Тест согласованности состояния UI

        // Исходное состояние
        assertEquals("", getFirstArgumentFieldValue(), "First field should be empty initially");
        assertEquals("", getSecondArgumentFieldValue(), "Second field should be empty initially");
        assertEquals("", getResultFieldValue(), "Result field should be empty initially");

        // После ввода и вычисления
        simulateUserInput("firstArgumentField", "8");
        simulateUserInput("secondArgumentField", "2");
        simulateButtonClick("plusButton");

        assertEquals("8", getFirstArgumentFieldValue(), "First field should maintain value");
        assertEquals("2", getSecondArgumentFieldValue(), "Second field should maintain value");
        assertEquals("10.0", getResultFieldValue(), "Result should show calculation result");

        // После очистки
        simulateUserInput("firstArgumentField", "");
        simulateUserInput("secondArgumentField", "");
        assertEquals("", getFirstArgumentFieldValue(), "First field should be clearable");
        assertEquals("", getSecondArgumentFieldValue(), "Second field should be clearable");
    }

    private void simulateUserInput(String fieldName, String value) {
        try {
            Field field = view.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            JTextField textField = (JTextField) field.get(view);
            textField.setText(value);
        } catch (Exception e) {
            fail("Failed to simulate user input: " + e.getMessage());
        }
    }

    private void simulateButtonClick(String buttonName) {
        try {
            Field field = view.getClass().getDeclaredField(buttonName);
            field.setAccessible(true);
            JButton button = (JButton) field.get(view);

            // Эмулируем нажатие кнопки
            for (java.awt.event.ActionListener listener : button.getActionListeners()) {
                listener.actionPerformed(
                        new java.awt.event.ActionEvent(button,
                                java.awt.event.ActionEvent.ACTION_PERFORMED,
                                button.getText())
                );
            }
        } catch (Exception e) {
            fail("Failed to simulate button click: " + e.getMessage());
        }
    }

    private String getFirstArgumentFieldValue() {
        return getTextFieldValue("firstArgumentField");
    }

    private String getSecondArgumentFieldValue() {
        return getTextFieldValue("secondArgumentField");
    }

    private String getResultFieldValue() {
        return getTextFieldValue("resultField");
    }

    private String getTextFieldValue(String fieldName) {
        try {
            Field field = view.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            JTextField textField = (JTextField) field.get(view);
            return textField.getText();
        } catch (Exception e) {
            fail("Failed to get text field value: " + e.getMessage());
            return "";
        }
    }
}