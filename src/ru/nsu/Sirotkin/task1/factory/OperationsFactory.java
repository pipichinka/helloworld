package ru.nsu.Sirotkin.task1.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OperationsFactory {
    private final Map<String, String> classMap;
    private final String fileName= "factory.cnf";

    public OperationsFactory(){
        classMap = new HashMap<>();
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
                classMap.put(currentLineSplited[0], currentLineSplited[1]);
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
