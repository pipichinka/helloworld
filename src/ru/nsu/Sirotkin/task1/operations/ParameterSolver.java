package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;

class ParameterSolver {
    static void solveParameters(Context context, String[] params, Double[] operands) throws OperationException{

        for (int i = 0; i < operands.length; i++){
            if (i < params.length){
                try {
                    operands[i] = Double.parseDouble(params[i]);
                }
                catch (NumberFormatException e){
                    operands[i] = context.getDefine(params[i]);
                    if (operands[i] == null){
                        throw new OperationException("no such define: " + params[i]);
                    }
                }
            }
            else{
                operands[i] = context.getFromStack();
                if (operands[i] == null){
                    throw new OperationException("stack is empty during parsing argument" + (i+1));
                }
            }
        }
    }
}
