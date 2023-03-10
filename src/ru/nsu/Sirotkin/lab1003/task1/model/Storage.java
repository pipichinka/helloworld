package ru.nsu.Sirotkin.lab1003.task1.model;

import java.util.Stack;

public class Storage <T> {
    final Stack<T> list;

    final int capacity;

    int currentIndex;


    public Storage(int maxCapacity) {
        if (maxCapacity < 0){
            throw new IllegalArgumentException();
        }
        capacity = maxCapacity;
        list = new Stack<>();
        currentIndex = 0;
    }

    public T get() throws InterruptedException {
        synchronized (list){
            while (isEmpty()){
                list.wait();
            }
            currentIndex--;
            list.notifyAll();
            return list.pop();
        }
    }

    public void put(T product) throws InterruptedException {
        synchronized (list){
            while (isFull()){
                list.wait();
            }
            list.push(product);
            list.notifyAll();
        }
        currentIndex++;
    }

    public synchronized boolean isFull(){
        return capacity == currentIndex;
    }

    public synchronized boolean isEmpty(){
        return currentIndex == 0;
    }
}
