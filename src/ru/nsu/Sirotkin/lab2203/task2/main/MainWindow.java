package ru.nsu.Sirotkin.lab2203.task2.main;

import ru.nsu.Sirotkin.lab1503.task2.model.Model;
import ru.nsu.Sirotkin.lab2203.task2.controller.SwingController;
import ru.nsu.Sirotkin.lab2203.task2.view.SwingView;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class MainWindow extends JFrame {

    private MainWindow(Model model){
        JLabel labelInt = new JLabel("0");
        setLayout(new FlowLayout());
        add(labelInt);
        Document textModel = new PlainDocument();

        JTextField textField = new JTextField(10);
        textField.setDocument(textModel);
        add(textField);

        JButton button = new JButton("OK");
        add(button);

        JLabel labelMessage = new JLabel("start");
        add(labelMessage);

        model.setListener( new SwingView(model, labelInt, labelMessage));
        button.setActionCommand("ok");
        SwingController controller = new SwingController(model,textModel);
        button.addActionListener(controller);
        addWindowListener(controller);
        pack();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            MainWindow w = new MainWindow(new Model("pip","record.txt"));
            w.setVisible(true);
        });
    }
}
