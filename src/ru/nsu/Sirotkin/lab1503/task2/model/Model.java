package ru.nsu.Sirotkin.lab1503.task2.model;

import ru.nsu.Sirotkin.lab1503.task2.view.ModelListener;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private int primeForCurrentInt;
    private int currentInt;
    private StatesOfGame state;

    private final Map<String, Integer> records;
    private ModelListener listener = null;

    private final String recordFile;

    private final String player;

    public Model(String player, String recordFile){
        this.recordFile = recordFile;
        this.player = player;
        records = new HashMap<>();
        try (var stream = new BufferedReader(new FileReader(recordFile))) {
            while (stream.ready()){
                String[] lineParts = stream.readLine().split(" ");
                if (lineParts.length != 2){
                    throw new RuntimeException("invalid record file");
                }
                records.put(lineParts[0], Integer.parseInt(lineParts[1]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (!records.containsKey(player)){
            records.put(player, 0);
        }
    }

    public void generateNextInt(){
        currentInt = (int) (Math.random() * 100);
        primeGenerator(currentInt);
        state = StatesOfGame.START;
        notifyListener();
    }

    public void checkInput(StatesOfGame state,int number){
        if (state == StatesOfGame.PRINTRECORDS){
            try (var stream = new BufferedWriter(new FileWriter(recordFile))) {
                for (var entry : records.entrySet()) {
                    stream.write(entry.getKey() + " " + entry.getValue() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.state = state;
        }
        else if (state != StatesOfGame.SUCCESS) {
            this.state = state;
        }
        else if (primeForCurrentInt == number){
            this.state = StatesOfGame.SUCCESS;
            records.put(player, records.get(player) + 1);
        }
        else{
            this.state = StatesOfGame.FAIL;
        }
        notifyListener();
    }

    private void notifyListener(){
        if (listener != null){
            listener.onModelChanged();
        }
    }

    public void setListener(ModelListener listener){
        this.listener = listener;
    }


    public int getCurrentInt(){
        return currentInt;
    }

    public StatesOfGame getState(){
        return state;
    }


    public Map<String, Integer> getRecords(){
        return records;
    }


    private boolean inPrime(int x){
        for (int i = 2; i < x/2; i++){
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }


    private void primeGenerator(int start){
        this.primeForCurrentInt = start;
        do {
            this.primeForCurrentInt++;
        } while (!inPrime(primeForCurrentInt));
    }
}
