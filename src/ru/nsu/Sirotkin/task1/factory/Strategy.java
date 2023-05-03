package ru.nsu.Sirotkin.task1.factory;

public interface Strategy {
    double execute(double a, double b);

    String getName();

    String getHelp();
}
