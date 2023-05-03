package ru.nsu.Sirotkin.task1.factory;

import ru.nsu.Sirotkin.task1.exceptions.FactoryConfigException;
import ru.nsu.Sirotkin.task1.exceptions.OperationCreatingException;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;
import ru.nsu.Sirotkin.task1.operations.BinaryOperation;
import ru.nsu.Sirotkin.task1.strateges.StrategyDivision;
import ru.nsu.Sirotkin.task1.strateges.StrategyMinus;
import ru.nsu.Sirotkin.task1.strateges.StrategyMultiply;
import ru.nsu.Sirotkin.task1.strateges.StrategyPlus;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OperationsFactory {


    private final Map<String, String> classNamesmap;


    private static final String FILE_NAME = "factory.cnf";


    private final Map<String, Operation> classesMap;


    public OperationsFactory() throws FactoryConfigException{
        classNamesmap = new HashMap<>();
        classesMap = new HashMap<>();
        try (InputStream stream = OperationsFactory.class.getResourceAsStream(FILE_NAME)){
            if (stream == null){
                throw new FactoryConfigException("can't open factory config file");
            }
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] currentLineSplited = currentLine.split(" ");
                if (currentLineSplited.length != 2) {
                    throw new FactoryConfigException("invalid factory config file");
                }
                classNamesmap.put(currentLineSplited[0], currentLineSplited[1]);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Operation getOperation(String name) throws OperationException {

        switch (name) {
            case "+" -> {
                return new BinaryOperation(new StrategyPlus());
            }
            case "-" -> {
                return new BinaryOperation(new StrategyMinus());
            }
            case "*" -> {
                return new BinaryOperation(new StrategyMultiply());
            }
            case "/" -> {
                return new BinaryOperation(new StrategyDivision());
            }
        }


        String operationName = name.toUpperCase();
        if (classesMap.containsKey(operationName)){
            return classesMap.get(operationName);
        }
        if (!classNamesmap.containsKey(operationName)){
            throw new OperationException("no such operation " + operationName);
        }
        try {
            Class<?> operationClass = Class.forName(classNamesmap.get(operationName));
            var constructor = operationClass.getConstructor();
            Operation result = (Operation) constructor.newInstance();
            classesMap.put(operationName, result);
            return result;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new OperationCreatingException(e);
        }
    }


    public String[] getOperationsNames() throws OperationException {
        try {
            var keys = classNamesmap.keySet().toArray();
            String[] result = new String[keys.length];
            for (int i = 0; i < result.length; i++){
                result[i] =(String) keys[i];
            }
            return result;
        }
        catch (ClassCastException e){
            throw new OperationException(e);
        }
    }

}
