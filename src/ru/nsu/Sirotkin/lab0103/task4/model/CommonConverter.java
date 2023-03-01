package ru.nsu.Sirotkin.lab0103.task4.model;

public class CommonConverter<T extends Integer> implements Converter<T> {

    @Override
    public String convert(T x) {
        return x.toString();
    }
}
