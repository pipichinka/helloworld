package ru.nsu.Sirotkin.lab0802.task9.ui;

import ru.nsu.Sirotkin.lab0802.task9.widgets.Diamond;
import ru.nsu.Sirotkin.lab0802.task9.widgets.Factory;

import java.util.Scanner;

public class UI {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        if (n % 2 == 0 ){
            System.out.println("can't print that diamond");
            return;
        }
        Diamond diamondEmpty = new Diamond(Factory.createEmpyLinePrinter());
        Diamond diamondFilled = new Diamond(Factory.createFilledLinePrinter());
        diamondEmpty.printDiamond(n);
        diamondFilled.printDiamond(n);
    }
}
