package ru.nsu.Sirotkin.lab1503.task2.main;

import ru.nsu.Sirotkin.lab1503.task2.controller.Controller;
import ru.nsu.Sirotkin.lab1503.task2.model.Model;
import ru.nsu.Sirotkin.lab1503.task2.model.ModelListener;
import ru.nsu.Sirotkin.lab1503.task2.view.View;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String player = scanner.nextLine();
        Model model = new Model(player, "out.txt");
        Controller  controller = new Controller(model);
        ModelListener view = new View(model);
        model.setListener(view);
        controller.start();
        controller.join();

    }
}
