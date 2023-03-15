package ru.nsu.Sirotkin.task1.context;

public interface Context {
    void setDefine(String defineKey, Double defineValue);


    void pushToStack(Double value);


    Double getFromStack();


    Double getDefine(String defineKey);

}
