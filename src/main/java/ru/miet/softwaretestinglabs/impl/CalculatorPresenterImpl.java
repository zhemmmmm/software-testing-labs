package ru.miet.softwaretestinglabs.impl;

import org.springframework.stereotype.Component;
import ru.miet.softwaretestinglabs.Calculator;
import ru.miet.softwaretestinglabs.CalculatorPresenter;
import ru.miet.softwaretestinglabs.CalculatorView;

@Component
public class CalculatorPresenterImpl implements CalculatorPresenter {

    private Calculator calculator;
    private CalculatorView view;

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '+'
     */
    @Override
    public void onPlusClicked(String req) {
        String aStr = view.getFirstArgumentAsString(req);
        String bStr = view.getSecondArgumentAsString(req);

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
    public void onMinusClicked(String req) {
        String aStr = view.getFirstArgumentAsString(req);
        String bStr = view.getSecondArgumentAsString(req);

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
    public void onDivideClicked(String req) {
        String aStr = view.getFirstArgumentAsString(req);
        String bStr = view.getSecondArgumentAsString(req);

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
    public void onMultiplyClicked(String req) {
        String aStr = view.getFirstArgumentAsString(req);
        String bStr = view.getSecondArgumentAsString(req);

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            view.printResult(calculator.multiply(a, b));
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }
}
