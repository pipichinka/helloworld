package ru.nsu.Sirotkin.task1;

import ru.nsu.Sirotkin.task1.calc.CalcInputStream;

import java.io.*;

public class FileCalcInputStream implements CalcInputStream, Closeable {


    private final BufferedReader stream;


    public FileCalcInputStream(String fileName) throws IOException {
        stream = new BufferedReader(new FileReader(fileName));
    }


    @Override
    public String readLine() throws IOException {

        return stream.readLine();

    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
