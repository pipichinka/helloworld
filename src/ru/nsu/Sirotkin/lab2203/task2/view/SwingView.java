package ru.nsu.Sirotkin.lab2203.task2.view;

import ru.nsu.Sirotkin.lab1503.task2.model.Model;
import ru.nsu.Sirotkin.lab1503.task2.model.StatesOfGame;
import ru.nsu.Sirotkin.lab1503.task2.model.ModelListener;

import javax.swing.*;

public class SwingView implements ModelListener {
    private final Model model;
    private final JLabel labelInt;

    private final JLabel labelMassage;
    public SwingView(Model model, JLabel labelInt, JLabel labelMassage){
        this.model = model;
        this.labelInt = labelInt;
        this.labelMassage = labelMassage;
    }

    @Override
    public void onModelChanged()  {
        if (model.getState() == StatesOfGame.START) {
            labelInt.setText("" + model.getCurrentInt());
        } else {
            labelMassage.setText(model.getState().toString());
        }
    }
}
