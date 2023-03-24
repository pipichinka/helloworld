package ru.nsu.Sirotkin.task1.operations;


import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.factory.Operation;

import java.security.InvalidParameterException;

class OperationPush implements Operation {
    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null || params == null){
            throw new InvalidParameterException("null pointers");
        }
        if (params.length != 1){
            throw new InvalidParameterException("invalid number of arguments");
        }
        Double[] parameters = ParameterSolver.solveParameters(context,params,1);
        context.pushToStack(parameters[0]);
        return "pushed to stack " + parameters[0];
    }
}
