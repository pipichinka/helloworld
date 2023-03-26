package ru.nsu.Sirotkin.task1.exceptions;

public class FileException extends BaseException{
    public FileException(Exception e){
        super(e);
    }

    public FileException(String massage){
        super(massage);
    }
}
