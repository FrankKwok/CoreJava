package com.github.frankkwok.corejava.v1ch14.synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Frank Kwok on 2017/4/9.
 */
class Bank {
    private double[] accounts;
    private Lock lock;
    private Condition condition;

    Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    void transfer(int from, int to, double amount) throws InterruptedException {
        lock.lock();
        try {
            if (accounts[from] < amount) {
                condition.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private double getTotalBalance() {
        lock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            lock.unlock();
        }
    }

    int size() {
        return accounts.length;
    }
}
