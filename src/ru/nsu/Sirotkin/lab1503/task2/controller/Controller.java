package ru.nsu.Sirotkin.lab1503.task2.controller;

import ru.nsu.Sirotkin.lab1503.task2.model.Model;
import ru.nsu.Sirotkin.lab1503.task2.model.StatesOfGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller extends Thread{

    private final Model model;

    private final Scanner scanner= new Scanner(System.in);
    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void run() {
        int input;
        while (true){
            model.generateNextInt();
            try{
                input = scanner.nextInt();
                if (input == -1){
                    model.checkInput(StatesOfGame.PRINTRECORDS,0);
                    return;
                }
                model.checkInput(StatesOfGame.SUCCESS,input);
            }
            catch (InputMismatchException e){
                scanner.nextLine();
                model.checkInput(StatesOfGame.NOTANUMBER,0);
            }


        }
    }
}
