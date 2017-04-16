package com.github.frankkwok.corejava.v1ch14.threadpool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author Frank Kwok on 2017/4/16.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk1.8.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            ExecutorService pool = Executors.newCachedThreadPool();
            MatchCounter matchCounter = new MatchCounter(new File(directory), keyword, pool);
            Future<Integer> result = pool.submit(matchCounter);

            try {
                System.out.println(result.get() + " matching files.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            pool.shutdown();
            int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
            System.out.println("largest pool size = " + largestPoolSize);
        }
    }
}

class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private ExecutorService pool;

    MatchCounter(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    @Override
    public Integer call() {
        int count = 0;
        List<Future<Integer>> futures = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter matchCounter = new MatchCounter(file, keyword, pool);
                    Future<Integer> future = pool.submit(matchCounter);
                    futures.add(future);
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
