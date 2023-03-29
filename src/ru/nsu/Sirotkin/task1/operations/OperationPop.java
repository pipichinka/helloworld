package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;


public class OperationPop implements Operation {


    private Double popValue;


    @Override
    public void performOperation(Context context, String[] params) throws OperationException {
        if (params.length != 0){
            throw new OperationException("POP does not require operands");
        }
        popValue = context.getFromStack();
        if (popValue == null){
            throw new OperationException("stack is empty");
        }
    }


    @Override
    public String name() {
        return "operation Pop";
    }


    @Override
    public String[] lastOperands() {
        return new String[0];
    }


    @Override
    public String lastResult() {
        return popValue.toString() + " was taken from stack";
    }

    @Override
    public String getHelpString() {
        return """
                Operation Pop is used to take value from the stack
                No operands should be given to this operation
                """;
    }
}
