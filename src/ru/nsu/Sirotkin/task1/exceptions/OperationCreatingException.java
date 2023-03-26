package ru.nsu.Sirotkin.task1.exceptions;

public class OperationCreatingException extends RuntimeException{
    public OperationCreatingException(Exception e){
        super(e);
    }
}
