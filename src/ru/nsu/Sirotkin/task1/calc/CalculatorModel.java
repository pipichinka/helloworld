package ru.nsu.Sirotkin.task1.calc;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.context.ContextImplementation;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;
import ru.nsu.Sirotkin.task1.exceptions.FactoryConfigException;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;
import ru.nsu.Sirotkin.task1.factory.OperationsFactory;


public class CalculatorModel {

    private final CalcInputStream commandStream;


    private final OperationsFactory operationsFactory;


    private final Context context;


    public CalculatorModel(CalcInputStream stream) throws BaseException {
        commandStream = stream;
        try {
            operationsFactory = new OperationsFactory();
        } catch (FactoryConfigException e) {
            throw new BaseException(e);
        }
        context = new ContextImplementation();
    }


    public void start() throws BaseException {
        while (true){
            String currentCommandLine = commandStream.readLine();
            if (currentCommandLine == null){
                return;
            }
            if (currentCommandLine.startsWith("#")){
                continue;
            }
            if (currentCommandLine.equals("EXIT")){
                return;
            }
            String[] currentCommandLineSplit = currentCommandLine.split(" ");
            if (currentCommandLineSplit.length == 0){
                continue;
            }
            try {
                Operation currentOperation = operationsFactory.getOperation(currentCommandLineSplit[0]);
                String[] operationParameters = new String[currentCommandLineSplit.length - 1];

                System.arraycopy(currentCommandLineSplit, 1, operationParameters, 0, currentCommandLineSplit.length - 1);
                currentOperation.performOperation(context, operationParameters);
            }
            catch (OperationException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
