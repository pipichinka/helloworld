package ru.nsu.Sirotkin.lab0802.task9.widgets;

public class Diamond {
    private final LinePrinter printer;
    public Diamond(LinePrinter linePrinter){
        printer = linePrinter;
    }


    private void printEmptyLine(int n){
        for (int i = 0; i < n; i++){
            System.out.print(' ');
        }
    }


    public void printDiamond(int n){
        if (n <= 0){
            return;
        }
        for (int i = 1; i <= n; i += 2){
            printEmptyLine((n - i) / 2);
            printer.printLine(i);
            printEmptyLine((n - i) / 2);
            System.out.print('\n');
        }
        for (int i = n-2; i >= 1; i -= 2){
            printEmptyLine((n - i) / 2);
            printer.printLine(i);
            printEmptyLine((n - i) / 2);
            System.out.print('\n');
        }
    }
}
