package ru.nsu.Sirotkin.lab1503.task2.model;

public enum StatesOfGame {
    SUCCESS("success"), FAIL("fail"), NOTANUMBER("not_a_number"), START("start"),
    PRINTRECORDS("print records");
    private final String string;

    StatesOfGame(String str){
        string = str;
    }

    @Override
    public String toString() {
        return string;
    }
}
