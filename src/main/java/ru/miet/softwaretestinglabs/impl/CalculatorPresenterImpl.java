package ru.miet.softwaretestinglabs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.miet.softwaretestinglabs.Calculator;
import ru.miet.softwaretestinglabs.CalculatorPresenter;
import ru.miet.softwaretestinglabs.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class CalculatorPresenterImpl implements CalculatorPresenter, ActionListener {

    private final Calculator calculator;
    private final CalculatorView view;

    @Autowired
    public CalculatorPresenterImpl(Calculator calculator, CalculatorView view) {
        this.calculator = calculator;
        this.view = view;

        if (view instanceof CalculatorViewImpl) {
            ((CalculatorViewImpl) view).setActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "+": onPlusClicked(); break;
            case "-": onMinusClicked(); break;
            case "*": onMultiplyClicked(); break;
            case "/": onDivideClicked(); break;
        }
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '+'
     */
    @Override
    public void onPlusClicked() {
        String aStr = view.getFirstArgumentAsString();
        String bStr = view.getSecondArgumentAsString();

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            view.printResult(calculator.sum(a, b));
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '-'
     */
    @Override
    public void onMinusClicked() {
        String aStr = view.getFirstArgumentAsString();
        String bStr = view.getSecondArgumentAsString();

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            view.printResult(calculator.subtract(a, b));
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '/'
     */
    @Override
    public void onDivideClicked() {
        String aStr = view.getFirstArgumentAsString();
        String bStr = view.getSecondArgumentAsString();

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            view.printResult(calculator.divide(a, b));
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '*'
     */
    @Override
    public void onMultiplyClicked() {
        String aStr = view.getFirstArgumentAsString();
        String bStr = view.getSecondArgumentAsString();

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            view.printResult(calculator.multiply(a, b));
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }
}
