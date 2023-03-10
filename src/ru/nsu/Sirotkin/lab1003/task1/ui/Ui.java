package ru.nsu.Sirotkin.lab1003.task1.ui;

import ru.nsu.Sirotkin.lab1003.task1.model.Storage;

import java.util.Scanner;

public class Ui {

    static class ProducerTread extends Thread{

        private final Storage<String> storage;


        private final int id;
        ProducerTread(Storage<String> storage, int id){
            this.storage = storage;
            this.id = id;
        }
        @Override
        public void run() {
            int counter = 0;
            while(!this.isInterrupted()){
                try {
                    storage.put("p" + id + "-" + counter);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter++;
            }
        }
    }


    static class CustomerThread extends Thread{

        private final Storage<String> storage;


        private final int id;

        public CustomerThread(Storage<String> storage,int id ){
            this.storage = storage;
            this.id = id;
        }
        @Override
        public void run() {
            while(!this.isInterrupted()){
                try {
                    System.out.println("c" + id + " consumes " + storage.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = scanner.nextInt();
        System.out.print("\n");
        System.out.print("Enter P: ");
        int P = scanner.nextInt();
        System.out.print("\n");
        System.out.print("Enter C: ");
        int C = scanner.nextInt();
        System.out.print("\n");
        Storage<String> storage = new Storage<>(N);

        ProducerTread[] producers = new ProducerTread[P];
        CustomerThread[] customers = new CustomerThread[C];
        for (int i = 0; i < P; i++){
            producers[i] = new ProducerTread(storage, i);
            producers[i].start();
        }
        for (int i = 0; i < C; i++){
            customers[i] = new CustomerThread(storage, i);
            customers[i].start();
        }

        for (int i = 0; i < P; i++){
            producers[i].join();
        }

        for (int i = 0; i < C; i++){
            customers[i].join();
        }


    }
}
