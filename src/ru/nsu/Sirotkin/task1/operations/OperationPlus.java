package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;

import java.security.InvalidParameterException;

class OperationPlus implements Operation {

    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null || params == null){
            throw new InvalidParameterException("null pointers");
        }
        Double operand1;
        Double operand2;
        if (params.length == 0){
            operand1 = context.getFromStack();
            if (operand1 == null){
                throw new InvalidParameterException("empty stack");
            }
            operand2 = context.getFromStack();
            if (operand2 == null){
                context.pushToStack(operand1);
                throw new InvalidParameterException("not enough numbers on stack");
            }

        } else if (params.length == 1) {
            if (params[0] == null){
                throw new InvalidParameterException("empty parameter given");
            }
            operand1 = context.getFromStack();
            if (operand1 == null){
                throw new RuntimeException("empty stack");
            }

            try {
                operand2 = Double.parseDouble(params[0]);
            }
            catch (NumberFormatException e){
                operand2 = context.getDefine(params[0]);
                if (operand2 == null){
                    throw new InvalidParameterException("invalid parameter" + params[0]);
                }
            }

        } else if (params.length == 2) {
            if (params[0] == null || params[1] == null){
                throw new InvalidParameterException("empty parameter given");
            }
            try {
                operand1 = Double.parseDouble(params[0]);
            }
            catch (NumberFormatException e){
                operand1 = context.getDefine(params[0]);
                if (operand1 == null){
                    throw new InvalidParameterException("invalid parameter" + params[0]);
                }
            }
            try {
                operand2 = Double.parseDouble(params[1]);
            }
            catch (NumberFormatException e){
                operand2 = context.getDefine(params[1]);
                if (operand2 == null){
                    throw new InvalidParameterException("invalid parameter" + params[1]);
                }
            }
        }
        else{
            throw new InvalidParameterException("invalid number of parameters" + params.length);
        }
        context.pushToStack(operand1 + operand2);
        return operand1 + " + " + operand2;
    }
}
