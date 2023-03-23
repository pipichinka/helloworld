package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;

import java.security.InvalidParameterException;

class OperationDefine implements Operation{

    @Override
    public String perfomOperation(Context context, String[] params) {
        if (context == null || params == null){
            throw new InvalidParameterException("null parameters");
        }
        if (params.length != 2){
            throw new InvalidParameterException("invalid number of arguments");
        }
        Double operand = null;
        try {
            operand = Double.parseDouble(params[0]);
        }
        catch (NumberFormatException ignored){}

        if (operand != null){
            throw new RuntimeException("invalid define name" + params[0]);
        }

        try {
            operand = Double.parseDouble(params[1]);
        }
        catch (NumberFormatException e){
            throw new RuntimeException("invalid define value" + params[1]);
        }

        context.setDefine(params[0], operand);
        return "set define " + params[0] + " as " + params[1];
    }
}
