package ru.nsu.Sirotkin.task1.calc;

import ru.nsu.Sirotkin.task1.context.Context;
import ru.nsu.Sirotkin.task1.context.ContextImplementation;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;
import ru.nsu.Sirotkin.task1.exceptions.FactoryConfigException;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.factory.Operation;
import ru.nsu.Sirotkin.task1.factory.OperationsFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class CalculatorModel {


    private final CalcInputStream commandStream;


    private final OperationsFactory operationsFactory;


    private final Context context;


    private final Logger logger;


    private void logOperation(Operation operation){
        String[] operationOperands = operation.lastOperands();
        String operandsLine;
        if (operationOperands.length == 0){
            operandsLine = "no operands.";
        } else if (operationOperands.length == 1) {
            operandsLine = operationOperands[0] + " operand.";
        }
        else{
            operandsLine = operationOperands[0] + " and " +operationOperands[1] + " operands. ";
        }
        logger.log(Level.INFO, operation.name() + " with " +operandsLine + "Ended with result: " + operation.lastResult() + "\n");
    }


    public CalculatorModel(CalcInputStream stream) throws BaseException {
        commandStream = stream;
        try {
            operationsFactory = new OperationsFactory();
        } catch (FactoryConfigException e) {
            throw new BaseException(e);
        }
        context = new ContextImplementation();

        try(InputStream ins = CalculatorModel.class.getResourceAsStream("logger.cnf")){
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(CalculatorModel.class.getName());
        }catch (Exception e){
            throw new BaseException(e);
        }
    }


    public void start() throws BaseException, IOException {
        while (true){
            String currentCommandLine = commandStream.readLine();
            if (currentCommandLine == null){
                return;
            }
            if (currentCommandLine.startsWith("#")){
                continue;
            }

            String[] currentCommandLineSplit = currentCommandLine.split(" ");
            if (currentCommandLineSplit.length == 0){
                logger.log(Level.WARNING, "empty line");
                System.err.println("empty line");
                continue;
            }

            try {
                Operation currentOperation = operationsFactory.getOperation(currentCommandLineSplit[0]);
                String[] operationParameters = new String[currentCommandLineSplit.length - 1];

                System.arraycopy(currentCommandLineSplit, 1, operationParameters, 0, currentCommandLineSplit.length - 1);
                currentOperation.performOperation(context, operationParameters);

                logOperation(currentOperation);
            }
            catch (OperationException e){
                logger.log(Level.WARNING, e.getMessage());
                System.err.println(e.getMessage());
            }
        }
    }

}
