package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;


public class OperationPrint implements Operation {


    private Double printValue;


    @Override
    public void performOperation(Context context, String[] params) throws OperationException {
        if (params.length != 0){
            throw new OperationException("PRINT does not require operands");
        }
        printValue = context.peekFromStack();
        if ( printValue == null){
            throw new OperationException("empty stack");
        }
        System.out.println(printValue);
    }

    @Override
    public String name() {
        return "operation Print";
    }

    @Override
    public String[] lastOperands() {
        return new String[0];
    }

    @Override
    public String lastResult() {
        return printValue + "was printed in console";
    }
}
