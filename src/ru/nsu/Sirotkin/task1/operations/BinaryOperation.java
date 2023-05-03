package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;
import ru.nsu.Sirotkin.task1.factory.Operation;
import ru.nsu.Sirotkin.task1.factory.Strategy;

public class BinaryOperation implements Operation {

    private final Strategy strategy;
    private final Double[] operands = new Double[2];

    public BinaryOperation(Strategy strategy){
        this.strategy = strategy;
    }



    @Override
    public void performOperation(Context context, String[] params) throws BaseException {
        ParameterSolver.solveParameters(context, params, operands);
        context.pushToStack(strategy.execute(operands[0], operands[1]));
    }

    @Override
    public String name() {
        return "BinaryOperation with strategy " + strategy.getName();
    }

    @Override
    public String[] lastOperands() {
        String[] result = new String[2];
        result[0] = operands[0].toString();
        result[1] = operands[1].toString();
        return result;
    }

    @Override
    public String lastResult() {
        return Double.toString(strategy.execute(operands[0], operands[1]));
    }

    @Override
    public String getHelpString() {
        return """
                Operation BinaryOperation is used to apply given Strategy to two numbers
                this operation gets up to two operands
                operands can be define values or numbers
                if given less operands than two uses values from stack
                result is pushed to stack.
                """ + "Current Strategy is " + strategy.getName() + " is used to " + strategy.getHelp();
    }
}
