package ru.nsu.Sirotkin.lab1702.task1.UI;

import ru.nsu.Sirotkin.lab1702.task1.CSVparser.CsvParser;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        System.out.print("\n");
        try(CsvParser parser = new CsvParser(fileName,",")) {
            int lineCounter = 1;
            while (!parser.isEof()){
                String[] currentLine = parser.parseNextLine();
                int s = 0;
                for (int i = 0; i < currentLine.length; i++){
                    try{
                        s += Integer.parseInt(currentLine[i]);
                    }
                    catch (NumberFormatException exception){
                        System.err.println("not a number at line: " + Integer.toString(lineCounter) + " at field: " + (i+1));
                    }
                }
                System.out.println("sum of " + lineCounter + " is: " + s);
                lineCounter++;
            }
        }
        catch (IOException ex){
            System.out.print(ex.getMessage());
        }

    }
}
