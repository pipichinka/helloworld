package ru.nsu.Sirotkin.lab1503.task2.view;

import ru.nsu.Sirotkin.lab1503.task2.model.Model;
import ru.nsu.Sirotkin.lab1503.task2.model.StatesOfGame;

public class View implements ModelListener {

    private final Model model;
    public View(Model model) {
        this.model = model;
    }

    @Override
    public void onModelChanged() {
        if (model.getState() == StatesOfGame.START){
            System.out.println(model.getCurrentInt());
        }
        else if (model.getState() == StatesOfGame.PRINTRECORDS){
            var records = model.getRecords();
            for (var entry : records.entrySet()) {
                System.out.println(entry.getKey() + "  " + entry.getValue());
            }
        }
        else {
            System.out.println(model.getState());
        }
    }
}
