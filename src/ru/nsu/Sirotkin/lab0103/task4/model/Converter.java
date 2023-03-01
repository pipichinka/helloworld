package ru.nsu.Sirotkin.lab0103.task4.model;

public interface Converter<T extends Integer> {
    public String convert(T x);
}
