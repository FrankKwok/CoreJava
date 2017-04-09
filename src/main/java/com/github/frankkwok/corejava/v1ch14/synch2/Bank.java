package com.github.frankkwok.corejava.v1ch14.synch2;

import java.util.Arrays;

/**
 * @author Frank Kwok on 2017/4/9.
 */
class Bank {
    private double[] accounts;

    Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        if (accounts[from] < amount) {
            wait();
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        notifyAll();
    }

    private synchronized double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }
}
