package ru.nsu.Sirotkin.task1.operations;


import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;

public class OperationPush implements Operation {


    private final Double[] operands = new Double[1];


    @Override
    public void performOperation(Context context, String[] params) throws OperationException{
        if (params.length != 1){
            throw new OperationException("invalid number of arguments");
        }
        ParameterSolver.solveParameters(context,params,operands);
        context.pushToStack(operands[0]);
    }


    @Override
    public String name() {
        return "operation Push";
    }


    @Override
    public String[] lastOperands() {
        String[] result = new String[1];
        result[0] = operands[0].toString();
        return result;
    }


    @Override
    public String lastResult() {
        return operands[0].toString() + " was pushed to stack";
    }
}
