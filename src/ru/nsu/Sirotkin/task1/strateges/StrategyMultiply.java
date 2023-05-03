package ru.nsu.Sirotkin.task1.strateges;

import ru.nsu.Sirotkin.task1.factory.Strategy;

public class StrategyMultiply implements Strategy {
    @Override
    public double execute(double a, double b) {
        return a * b;
    }

    @Override
    public String getName() {
        return "Multiply";
    }

    @Override
    public String getHelp() {
        return "multiply first parameter on second";
    }
}
