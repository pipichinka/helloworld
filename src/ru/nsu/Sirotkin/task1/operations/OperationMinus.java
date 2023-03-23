package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;

import java.security.InvalidParameterException;

class OperationMinus implements Operation{

    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null || params == null){
            throw new InvalidParameterException("null pointers");
        }
        Double[] operands = ParameterSolver.solveParameters(context, params, 2);
        context.pushToStack(operands[0] - operands[1]);
        return operands[0] + "-" + operands[1];
    }
}