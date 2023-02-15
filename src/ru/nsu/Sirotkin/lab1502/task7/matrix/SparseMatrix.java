package ru.nsu.Sirotkin.lab1502.task7.matrix;

import java.util.*;

public class SparseMatrix {

    private static class  Dot{
        private int x;
        private int y;
        Dot(int i, int j){
            x = i;
            y = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dot dot = (Dot) o;
            return x == dot.x && y == dot.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private Map<Dot,Integer> matrix;


    private int height;


    private int length;


    public SparseMatrix(int N, int M){
        height = N;
        length = M;
        matrix = new HashMap<Dot, Integer>();
    }


    public void set(int i, int j, int value){
        if (i < height && j <length && i >= 0 && j >= 0 ){
            matrix.put(new Dot(i, j), value);
        }
    }


    public int get(int i, int j){
        Dot tmp_dot = new Dot(i,j);
        return matrix.getOrDefault(tmp_dot, 0);
    }
}
