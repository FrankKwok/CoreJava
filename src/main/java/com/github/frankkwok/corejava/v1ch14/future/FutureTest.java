package com.github.frankkwok.corejava.v1ch14.future;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author Frank Kwok on 2017/4/16.
 */
public class FutureTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk1.8.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            MatchCounter matchCounter = new MatchCounter(new File(directory), keyword);
            FutureTask<Integer> futureTask = new FutureTask<>(matchCounter);
            Thread thread = new Thread(futureTask);
            thread.start();

            try {
                System.out.println(futureTask.get() + " matching files.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;

    MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() {
        int count = 0;
        File[] files = directory.listFiles();
        List<Future<Integer>> futures = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter matchCounter = new MatchCounter(file, keyword);
                    FutureTask<Integer> futureTask = new FutureTask<>(matchCounter);
                    futures.add(futureTask);
                    Thread thread = new Thread(futureTask);
                    thread.start();
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
            }
        }

        for (Future<Integer> future : futures) {
            try {
                count += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private boolean search(File file) {
        try (Scanner in = new Scanner(file, "UTF-8")) {
            while (in.hasNextLine()) {
                if (in.nextLine().contains(keyword)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
