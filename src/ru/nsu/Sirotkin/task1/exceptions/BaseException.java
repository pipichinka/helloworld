package ru.nsu.Sirotkin.task1.exceptions;

public class BaseException extends Exception{


    public BaseException(String massage){
        super(massage);
    }


    public BaseException(Exception e){
        super(e);
    }

}
