package ru.nsu.Sirotkin.lab0802.task9.widgets;

class LinePrinterRationalizationTwo implements LinePrinter{
    @Override
    public void printLine(int n) {
        if (n >= 1){
            System.out.print('*');
            if (n >= 3){
                for (int i = 0; i < n-2; i++){
                    System.out.print(' ');
                }
            }
            if (n >= 2){
                System.out.print('*');
            }
        }
    }
}
