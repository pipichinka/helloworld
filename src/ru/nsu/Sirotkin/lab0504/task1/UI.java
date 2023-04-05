package ru.nsu.Sirotkin.lab0504.task1;

import java.util.Scanner;
import java.util.concurrent.*;

public class UI {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = scanner.nextInt();
        System.out.print("Enter M: ");
        int M = scanner.nextInt();
        CyclicBarrier barrier = new CyclicBarrier(N);
        Future[] futures = new Future[N];
        for (int i = 0; i < N; i++) {
            int treadIndex = i;
            Callable<String> task = () -> {
                while (!Thread.interrupted()) {
                    for (int j = 0; j < M; j++) {
                        System.out.println("Tread " + treadIndex + "-" + j);
                        try {
                            barrier.await();
                            Thread.sleep(1000);
                        } catch (InterruptedException | BrokenBarrierException e) {
                            return "Tread " + treadIndex + " finished.\n";
                        }
                    }
                }
                return "Tread " + treadIndex + " finished.\n";
            };
            futures[i] = service.submit(task);
        }
        Thread.sleep(5000);
        for (int i = 0; i < N; i++){
            futures[i].cancel(true);
        }
        service.shutdown();
    }
}
