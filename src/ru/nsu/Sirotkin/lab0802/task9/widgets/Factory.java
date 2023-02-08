package ru.nsu.Sirotkin.lab0802.task9.widgets;

public class Factory {
    static public LinePrinter createFilledLinePrinter(){
        return new LinePrinterRationalizationOne();
    }


    static public LinePrinter createEmpyLinePrinter(){
        return new LinePrinterRationalizationTwo();
    }
}
