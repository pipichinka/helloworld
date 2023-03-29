package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;


public class OperationSQRT implements Operation {


    private final Double[] operands = new Double[1];


    private Double result;


    @Override
    public void performOperation(Context context, String[] params) throws OperationException {
        ParameterSolver.solveParameters(context, params, operands);
        result = Math.sqrt(operands[0]);
        context.pushToStack(result);
    }


    @Override
    public String name() {
        return "operation SQRT";
    }


    @Override
    public String[] lastOperands() {
        String[] result = new String[1];
        result[0] = operands[0].toString();
        return result;
    }


    @Override
    public String lastResult() {
        return result.toString();
    }

    @Override
    public String getHelpString() {
        return """
                Operation SQRT is used to take a square root from a number
                this operation gets up to one operands
                if given zero operands, uses value from stack
                result is pushed to stack
                """;
    }
}
