package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.factory.Operation;

import java.security.InvalidParameterException;

class OperationPlus implements Operation {

    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null || params == null){
            throw new InvalidParameterException("null pointer arguments");
        }
        Double[] parameters = ParameterSolver.solveParameters(context, params ,2);
        context.pushToStack(parameters[0] + parameters[1]);
        return parameters[0] + " + " + parameters[0];
    }
}
