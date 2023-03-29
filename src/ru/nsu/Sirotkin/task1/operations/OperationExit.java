package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;

public class OperationExit implements Operation {
    @Override
    public void performOperation(Context context, String[] params) throws BaseException {
        if (params.length != 0){
            throw new OperationException("Exit does not require operands");
        }
        throw new BaseException("Bye");
    }

    @Override
    public String name() {
        return "operation Exit";
    }

    @Override
    public String[] lastOperands() {
        return new String[0];
    }

    @Override
    public String lastResult() {
        return null;
    }

    @Override
    public String getHelpString() {
        return """
                Operation Exit is used to close app
                No operands should be given to this operation
                """;
    }
}
