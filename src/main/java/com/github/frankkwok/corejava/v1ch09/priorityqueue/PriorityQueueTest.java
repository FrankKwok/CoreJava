package com.github.frankkwok.corejava.v1ch09.priorityqueue;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * @author Frank Kwok on 2017/4/5.
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(LocalDate.of(2000, 1, 1));
        priorityQueue.add(LocalDate.of(1992, 7, 8));
        priorityQueue.add(LocalDate.of(1991, 2, 26));
        priorityQueue.add(LocalDate.of(1996, 6, 20));

        System.out.println("Iterating over elements...");
        for (LocalDate date : priorityQueue) {
            System.out.println(date);
        }
        System.out.println("Removing elements...");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }
    }
}
