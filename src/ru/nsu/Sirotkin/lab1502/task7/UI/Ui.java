package ru.nsu.Sirotkin.lab1502.task7.UI;

import ru.nsu.Sirotkin.lab1502.task7.matrix.SparseMatrix;

import java.util.Random;
import java.util.Scanner;

public class Ui {
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N:");
        int N = scanner.nextInt();
        if (N <= 0){
            System.out.print("N can't be lower than 1");
            return;
        }
        System.out.print("\nEnter M:");
        int M = scanner.nextInt();
        if (M <= 0){
            System.out.print("M can't be lower than 1");
            return;
        }
        SparseMatrix matrix = new SparseMatrix(N,M);
        Random random = new Random();
        for (int i = 0; i < N/3; i++){
            for (int j = 0; j < M/4; j++){
                matrix.set(i, j, random.nextInt(0, 100));
            }
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.print('\n');
        }
    }
}
