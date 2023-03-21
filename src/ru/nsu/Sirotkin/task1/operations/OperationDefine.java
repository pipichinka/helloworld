package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;

import java.security.InvalidParameterException;

public class OperationDefine implements Operation{

    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null || params == null){
            throw new InvalidParameterException("null parameters");
        }
        return null;
    }
}
