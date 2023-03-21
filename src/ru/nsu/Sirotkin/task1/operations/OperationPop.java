package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;

import java.security.InvalidParameterException;

class OperationPop implements Operation{
    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null ) {
            throw new InvalidParameterException("null pointers");
        }
        if (params != null){
            throw new InvalidParameterException("arguments is not empty  ");
        }
        var popValue = context.getFromStack();
        if (popValue == null){
            throw new RuntimeException("stack is empty");
        }
        return "poped from stack " + popValue;
    }
}
