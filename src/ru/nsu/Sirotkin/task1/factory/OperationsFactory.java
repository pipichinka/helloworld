package ru.nsu.Sirotkin.task1.factory;

import ru.nsu.Sirotkin.task1.exceptions.FactoryConfigException;
import ru.nsu.Sirotkin.task1.exceptions.OperationCreatingException;
import ru.nsu.Sirotkin.task1.exceptions.OperationException;

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
        if (classesMap.containsKey(name)){
            return classesMap.get(name);
        }
        if (!classNamesmap.containsKey(name)){
            throw new OperationException("no such operation " + name);
        }
        try {
            Class<?> operationClass = Class.forName(classNamesmap.get(name));
            var constructor = operationClass.getConstructor();
            Operation result = (Operation) constructor.newInstance();
            classesMap.put(name, result);
            return result;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new OperationCreatingException(e);
        }
    }




}
