package ru.nsu.Sirotkin.task1.strateges;

import ru.nsu.Sirotkin.task1.factory.Strategy;

public class StrategyDivision implements Strategy {
    @Override
    public double execute(double a, double b) {
        return a / b;
    }

    @Override
    public String getName() {
        return "Division";
    }

    @Override
    public String getHelp() {
        return "divides first parameter by second";
    }
}
