package ru.miet.softwaretestinglabs;

public interface CalculatorPresenter {
    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '+'
     */
    void onPlusClicked(String req);

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '-'
     */
    void onMinusClicked(String req);

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '/'
     */
    void onDivideClicked(String req);

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '*'
     */
    void onMultiplyClicked(String req);
}
