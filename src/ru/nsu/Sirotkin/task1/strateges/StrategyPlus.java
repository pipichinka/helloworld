package ru.nsu.Sirotkin.task1.strateges;

import ru.nsu.Sirotkin.task1.factory.Strategy;

public class StrategyPlus implements Strategy {
    @Override
    public double execute(double a, double b) {
        return a + b;
    }

    @Override
    public String getName() {
        return "Plus";
    }

    @Override
    public String getHelp() {
        return "adds first parameter to second";
    }
}
