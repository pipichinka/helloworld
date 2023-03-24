package ru.nsu.Sirotkin.task1.factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OperationsFactory {
    private final Map<String, String> classNamesmap;


    private final String fileName= "factory.cnf";


    private final Map<String, Operation> classesMap;


    public OperationsFactory(){
        classNamesmap = new HashMap<>();
        classesMap = new HashMap<>();
        try (InputStream stream = OperationsFactory.class.getResourceAsStream(fileName)){
            if (stream == null){
                throw new RuntimeException("can't open factory config file");
            }
            Scanner scanner = new Scanner(stream);
            while (true){
                String currentLine = scanner.nextLine();
                if (currentLine == null){
                    break;
                }
                String[] currentLineSplited = currentLine.split(" ");
                if (currentLineSplited.length != 2){
                    throw new RuntimeException("invalid factory config file");
                }
                classNamesmap.put(currentLineSplited[0], currentLineSplited[1]);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Operation getOperation(String name) {
        if (classesMap.containsKey(name)){
            return classesMap.get(name);
        }
        if (!classNamesmap.containsKey(name)){
            throw new RuntimeException("no such operation " + name);
        }
        try {
            Class<?> operationClass = Class.forName(name);
            var constructor = operationClass.getConstructor();
            Operation result = (Operation) constructor.newInstance();
            classesMap.put(name, result);
            return result;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }




}
