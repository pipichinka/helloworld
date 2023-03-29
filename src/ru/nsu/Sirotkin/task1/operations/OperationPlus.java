package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;


public class OperationPlus implements Operation {


    private final Double[] operands = new Double[2];


    @Override
    public void performOperation(Context context, String[] params) throws OperationException {
        ParameterSolver.solveParameters(context, params ,operands);
        context.pushToStack(operands[0] + operands[1]);
    }


    @Override
    public String name() {
        return "operation Plus";
    }


    @Override
    public String[] lastOperands() {
        String[] result = new String[2];
        result[0] = operands[0].toString();
        result[1] = operands[1].toString();
        return result;
    }


    @Override
    public String lastResult() {
        return Double.toString(operands[0] + operands[1]);
    }

    @Override
    public String getHelpString() {
        return """
                Operation Plus is used to plus two numbers
                this operation gets up to two operands
                operands can be define values or numbers
                if given less operands than two uses values from stack
                result is pushed to stack
                """;
    }
}
