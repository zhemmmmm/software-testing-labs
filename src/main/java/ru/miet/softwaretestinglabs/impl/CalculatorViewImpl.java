package ru.miet.softwaretestinglabs.impl;

import org.springframework.stereotype.Component;
import ru.miet.softwaretestinglabs.CalculatorView;

@Component
public class CalculatorViewImpl implements CalculatorView {

    /**
     * Отображает результат вычисления
     *
     * @param result
     */
    @Override
    public void printResult(double result) {
        System.out.println(result);
    }

    /**
     * Показывает ошибку, например деление на 0, пустые аргументы и прочее
     *
     * @param message
     */
    @Override
    public void displayError(String message) {
        System.err.println(message);
    }

    /**
     * Возвращает значение, введенное в поле первого аргументы
     */
    @Override
    public String getFirstArgumentAsString(String req) {
        return req.split(" ")[0].trim();
    }

    /**
     * Возвращает значение, введенное в поле второго аргументы
     */
    @Override
    public String getSecondArgumentAsString(String req) {
        return req.split(" ")[1].trim();
    }
}
