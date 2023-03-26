package ru.nsu.Sirotkin.task1;

import ru.nsu.Sirotkin.task1.calc.CalcInputStream;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;
import ru.nsu.Sirotkin.task1.exceptions.FileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileCalcInputStream implements CalcInputStream {


    private final BufferedReader stream;


    public FileCalcInputStream(String fileName) throws BaseException {
        try {
            stream = new BufferedReader(new FileReader(fileName));
        }
        catch (FileNotFoundException e){
            throw new FileException(e);
        }
    }


    @Override
    public String readLine() throws BaseException {
        try {
            return stream.readLine();
        }
        catch (IOException e){
            throw new FileException(e);
        }
    }
}
