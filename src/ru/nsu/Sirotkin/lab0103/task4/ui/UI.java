package ru.nsu.Sirotkin.lab0103.task4.ui;

import ru.nsu.Sirotkin.lab0103.task4.model.CommonConverter;
import ru.nsu.Sirotkin.lab0103.task4.model.Converter;
import ru.nsu.Sirotkin.lab0103.task4.model.Printer;
import ru.nsu.Sirotkin.lab0103.task4.model.RomanConverter;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UI {

    private enum EnumMode{
        COMMON(new CommonConverter<Integer>()),
        ROMAN(new RomanConverter<Integer>());

        private final Converter<Integer> converter;

        private EnumMode(Converter<Integer> con){
            converter = con;
        }
        Converter<Integer> getConverter(){
            return converter;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter mode (COMMON or ROMAN): ");
        String inputLine;
        try {
            inputLine = scanner.nextLine();
        }
        catch (NoSuchElementException exception){
            System.out.println("no mode was given");
            return;
        }
        EnumMode mode = null;
        try {
            mode = EnumMode.valueOf(inputLine);
        }
        catch (IllegalArgumentException e){
            System.err.println("incorrect mode given. supported modes: ROMAN COMMON");
            return;
        }
        /*if (mode == EnumMode.ROMAN){
            try(Printer<Integer> printer = new Printer<Integer>("out.txt", new RomanConverter<>())) {
                for (int i = 1; i <= 1000; i++){
                    printer.print(i);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try(Printer<Integer> printer = new Printer<Integer>("out.txt", new CommonConverter<>())) {
                for (int i = 1; i <= 1000; i++){
                    printer.print(i);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/


        try {
            try(Printer<Integer> printer = new Printer<Integer>("out.txt", mode.getConverter())) {
                for (int i = 1; i <= 1000; i++){
                    printer.print(i);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
