package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.factory.Operation;

import java.security.InvalidParameterException;

class OperationSQRT implements Operation {

    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null || params == null){
            throw new InvalidParameterException("null pointers");
        }
        Double[] operands = ParameterSolver.solveParameters(context, params, 1);
        context.pushToStack(Math.sqrt(operands[0]));
        return "SQRT of " + operands[0];
    }
}
