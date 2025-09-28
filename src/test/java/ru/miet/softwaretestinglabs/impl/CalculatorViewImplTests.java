package ru.miet.softwaretestinglabs.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorViewImplTests {

    private CalculatorViewImpl view;

    @BeforeEach
    void setUp() {
        view = new CalculatorViewImpl();
    }

    @Test
    void testWindowProperties() {
        assertEquals("Calculator", view.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
        assertInstanceOf(GridLayout.class, view.getContentPane().getLayout());

        GridLayout layout = (GridLayout) view.getContentPane().getLayout();
        assertEquals(5, layout.getRows());
        assertEquals(2, layout.getColumns());
    }

    @Test
    void testAllComponentsExist() {
        // Проверяем, что все компоненты созданы
        assertNotNull(getField(view, "firstArgumentField"));
        assertNotNull(getField(view, "secondArgumentField"));
        assertNotNull(getField(view, "resultField"));
        assertNotNull(getField(view, "plusButton"));
        assertNotNull(getField(view, "minusButton"));
        assertNotNull(getField(view, "multiplyButton"));
        assertNotNull(getField(view, "divideButton"));
    }

    @Test
    void testResultFieldIsNotEditable() {
        JTextField resultField = (JTextField) getField(view, "resultField");
        assertFalse(resultField.isEditable());
    }

    @Test
    void testButtonTexts() {
        assertEquals("+", ((JButton) getField(view, "plusButton")).getText());
        assertEquals("-", ((JButton) getField(view, "minusButton")).getText());
        assertEquals("*", ((JButton) getField(view, "multiplyButton")).getText());
        assertEquals("/", ((JButton) getField(view, "divideButton")).getText());
    }

    @Test
    void testGetFirstArgumentAsString() {
        JTextField firstField = (JTextField) getField(view, "firstArgumentField");
        firstField.setText("123.45");

        assertEquals("123.45", view.getFirstArgumentAsString());
    }

    @Test
    void testGetSecondArgumentAsString() {
        JTextField secondField = (JTextField) getField(view, "secondArgumentField");
        secondField.setText("67.89");

        assertEquals("67.89", view.getSecondArgumentAsString());
    }

    @Test
    void testPrintResult() {
        JTextField resultField = (JTextField) getField(view, "resultField");

        view.printResult(123.456);
        assertEquals("123.456", resultField.getText());

        view.printResult(-78.9);
        assertEquals("-78.9", resultField.getText());
    }

    @Test
    void testSetActionListener() {
        java.awt.event.ActionListener listener = mock(java.awt.event.ActionListener.class);

        view.setActionListener(listener);

        JButton plusButton = (JButton) getField(view, "plusButton");
        JButton minusButton = (JButton) getField(view, "minusButton");
        JButton multiplyButton = (JButton) getField(view, "multiplyButton");
        JButton divideButton = (JButton) getField(view, "divideButton");

        assertEquals(1, plusButton.getActionListeners().length);
        assertEquals(1, minusButton.getActionListeners().length);
        assertEquals(1, multiplyButton.getActionListeners().length);
        assertEquals(1, divideButton.getActionListeners().length);
    }

    private Object getField(Object object, String fieldName) {
        try {
            var field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении поля: " + fieldName, e);
        }
    }
}