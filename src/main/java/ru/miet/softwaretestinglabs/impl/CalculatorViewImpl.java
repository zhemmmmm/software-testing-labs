package ru.miet.softwaretestinglabs.impl;

import org.springframework.stereotype.Component;
import ru.miet.softwaretestinglabs.CalculatorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Component
public class CalculatorViewImpl extends JFrame implements CalculatorView {
    private JTextField firstArgumentField;
    private JTextField secondArgumentField;
    private JTextField resultField;
    private JButton plusButton;
    private JButton minusButton;
    private JButton multiplyButton;
    private JButton divideButton;

    public CalculatorViewImpl() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        firstArgumentField = new JTextField();
        secondArgumentField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        plusButton = new JButton("+");
        minusButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        add(new JLabel("First Number:"));
        add(firstArgumentField);
        add(new JLabel("Second Number:"));
        add(secondArgumentField);
        add(new JLabel("Result:"));
        add(resultField);
        add(plusButton);
        add(minusButton);
        add(multiplyButton);
        add(divideButton);

        pack();
        setLocationRelativeTo(null);
    }

    public void setActionListener(ActionListener listener) {
        plusButton.addActionListener(listener);
        minusButton.addActionListener(listener);
        multiplyButton.addActionListener(listener);
        divideButton.addActionListener(listener);
    }

    @Override
    public String getFirstArgumentAsString() {
        return firstArgumentField.getText();
    }

    @Override
    public String getSecondArgumentAsString() {
        return secondArgumentField.getText();
    }

    @Override
    public void printResult(double result) {
        resultField.setText(String.valueOf(result));
    }

    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}