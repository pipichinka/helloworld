package ru.nsu.Sirotkin.lab0802.task9.widgets;

class LinePrinterRationalizationOne implements LinePrinter{
    @Override
    public void printLine(int n) {
        if (n > 0){
            for (int i = 0; i < n; i++){
                System.out.print('*');
            }
        }
    }
}
