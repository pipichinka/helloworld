package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;


public class OperationDefine implements Operation {


    private String operand1;


    private Double operand2;


@Override
    public void performOperation(Context context, String[] params) throws OperationException {
        if (params.length != 2){
            throw new OperationException("invalid number of arguments");
        }
        Double tmp = null;
        try {
            tmp = Double.parseDouble(params[0]);
        }
        catch (NumberFormatException ignored){}

        if (tmp != null){
            throw new OperationException("invalid define name: " + params[0]);
        }
        operand1 = params[0];
        try {
            operand2 = Double.parseDouble(params[1]);
        }
        catch (NumberFormatException e){
            throw new OperationException("invalid define value: " + params[1]);
        }

        context.setDefine(params[0], operand2);
    }


    @Override
    public String name() {
        return "operation Define";
    }


    @Override
    public String[] lastOperands() {
        String[] result = new String[2];
        result[0] = operand1;
        result[1] = operand2.toString();
        return result;
    }


    @Override
    public String lastResult() {
        return "value " + operand2.toString() + " was defined as " + operand1;
    }
}
