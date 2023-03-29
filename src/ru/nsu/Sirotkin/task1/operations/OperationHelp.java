package ru.nsu.Sirotkin.task1.operations;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.exceptions.FactoryConfigException;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;
import ru.nsu.Sirotkin.task1.factory.OperationsFactory;

public class OperationHelp implements Operation {
    @Override
    public void performOperation(Context context, String[] params) throws OperationException {
        try {
            OperationsFactory factory = new OperationsFactory();
            String[] operations = factory.getOperationsNames();
            for (String operation : operations) {
                System.out.println(operation);
                System.out.println("    " + factory.getOperation(operation).getHelpString());
                System.out.print("\n");
            }
        } catch (FactoryConfigException e) {
            throw new OperationException(e);
        }

    }

    @Override
    public String name() {
        return "operation Help";
    }

    @Override
    public String[] lastOperands() {
        return new String[0];
    }

    @Override
    public String lastResult() {
        return "Help was printed to console";
    }

    @Override
    public String getHelpString() {
        return "Operation Help prints to console help page.\n";
    }
}
