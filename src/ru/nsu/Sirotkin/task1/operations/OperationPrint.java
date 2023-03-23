package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;

import java.security.InvalidParameterException;

class OperationPrint implements Operation{

    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null){
            throw new InvalidParameterException("null pointer");
        }
        if (params != null){
            throw new InvalidParameterException("print does not require operands");
        }
        if (context.peekFromStack() == null){
            throw new InvalidParameterException("empty stack");
        }
        System.out.println(context.peekFromStack());
        return "print in console " + context.peekFromStack();
    }
}
