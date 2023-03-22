package ru.nsu.Sirotkin.lab2203.task2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ru.nsu.Sirotkin.lab1503.task2.model.Model;
import ru.nsu.Sirotkin.lab1503.task2.model.StatesOfGame;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class SwingController extends WindowAdapter implements ActionListener {
    private final Model model;
    private final Document document;

    public SwingController(Model model, Document document){
        this.model = model;
        this.document = document;
        model.generateNextInt();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            try {
                model.checkInput(StatesOfGame.SUCCESS, Integer.parseInt(document.getText(0, document.getLength())));
                model.generateNextInt();
            } catch (BadLocationException ex) {
                model.generateNextInt();
                System.out.println(ex.getMessage());
            } catch (NumberFormatException ignore) {
                model.generateNextInt();
                model.checkInput(StatesOfGame.NOTANUMBER, 0);
            }
        });
    }

    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().setVisible(false);
        e.getWindow().dispose();
    }
}
