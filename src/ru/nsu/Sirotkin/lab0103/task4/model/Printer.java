package ru.nsu.Sirotkin.lab0103.task4.model;

import java.io.*;

public class Printer <T extends Integer> implements Closeable {
    private final BufferedWriter stream;
    private final Converter<T> converter;

    public Printer(String fileName,Converter<T> con) throws IOException {
        stream = new BufferedWriter(new FileWriter(fileName));
        converter = con;
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }

    public void print(T x) throws IOException {
        stream.write(converter.convert(x) + "\n");
    }

}
