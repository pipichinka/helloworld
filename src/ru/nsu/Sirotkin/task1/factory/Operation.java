package ru.nsu.Sirotkin.task1.factory;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;

public interface Operation {


    void performOperation(Context context, String[] params) throws BaseException;


    String name();


    String[] lastOperands();


    String  lastResult();


    String getHelpString();
}
