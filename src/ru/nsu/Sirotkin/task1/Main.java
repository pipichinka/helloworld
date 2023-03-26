package ru.nsu.Sirotkin.task1;

import ru.nsu.Sirotkin.task1.calc.CalcInputStream;
import ru.nsu.Sirotkin.task1.calc.CalculatorModel;
import ru.nsu.Sirotkin.task1.exceptions.BaseException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        CalcInputStream inputStream;
        if (args.length == 0){
            inputStream = new CalcInputStream() {
                private final Scanner scanner= new Scanner(System.in);
                @Override
                public String readLine() {
                    return scanner.nextLine();
                }
            };
        }
        else if (args.length == 1) {
            try {
                inputStream = new FileCalcInputStream(args[0]);
            } catch (BaseException e) {
                System.err.println(e.getMessage());
                return;
            }
        }
        else{
            System.err.println("too many args given");
            return;
        }


        try {
            CalculatorModel model = new CalculatorModel(inputStream);
            model.start();
        }
        catch (BaseException e){
            System.err.println(e.getMessage());
        }
    }
}
