package ru.nsu.Sirotkin.lab1702.task1.CSVparser;

import java.io.*;

public class CsvParser implements Closeable {
    private final BufferedReader stream;
    private final  String separator;
    public CsvParser(String fileName, String sep) throws FileNotFoundException {
        stream = new BufferedReader(new FileReader(fileName));
        separator = sep;
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }

    public String[] parseNextLine() throws IOException {
        String line = stream.readLine();
        return line.split(separator);
    }


    public boolean isEof() throws IOException {
        return !stream.ready();
    }
}
