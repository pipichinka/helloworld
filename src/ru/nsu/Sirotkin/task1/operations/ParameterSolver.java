package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;

class ParameterSolver {
    static Double[] solveParameters(Context context, String[] params, int numberOfParameters){
        Double[] result = new Double[numberOfParameters];

        for (int i = 0; i < numberOfParameters; i++){
            if (params.length <= i + 1){
                try {
                    result[i] = Double.parseDouble(params[i]);
                }
                catch (NumberFormatException e){
                    result[i] = context.getDefine(params[i]);
                    if (result[i] == null){
                        throw new RuntimeException("no such define: " + params[i]);
                    }
                }
            }
            else{
                result[i] = context.getFromStack();
                if (result[i] == null){
                    throw new RuntimeException("stack is empty during parsing argument" + (i+1));
                }
            }
        }

        return result;
    }
}
