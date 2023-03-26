package ru.nsu.Sirotkin.task1.factory;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;

public interface Operation {


    void performOperation(Context context, String[] params) throws OperationException;


    String name();


    String[] lastOperands();


    String  lastResult();
}
