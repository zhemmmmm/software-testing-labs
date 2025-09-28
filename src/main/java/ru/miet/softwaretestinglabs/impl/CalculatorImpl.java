package ru.miet.softwaretestinglabs.impl;

import org.springframework.stereotype.Component;
import ru.miet.softwaretestinglabs.Calculator;

import static java.lang.Math.abs;

@Component
public class CalculatorImpl implements Calculator {

    /**
     * Вычисляет сумму двух чисел
     */
    @Override
    public double sum(double a, double b) {
        return a + b;
    }

    /**
     * Вычисляет разность двух чисел a - b
     */
    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Вычисляет произведение двух чисел
     */
    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Вычисляет отношение числа а к числу b.
     * Должен выбросить {@link ArithmeticException} если |b| < 10e-8
     */
    @Override
    public double divide(double a, double b) {
        if (abs(b) > 10e-8) {
            return a / b;
        }
        throw new ArithmeticException("|b| должен быть больше чем 10e-8");
    }
}
