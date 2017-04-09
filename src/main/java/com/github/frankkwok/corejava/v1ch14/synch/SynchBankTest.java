package com.github.frankkwok.corejava.v1ch14.synch;

/**
 * @author Frank Kwok on 2017/4/9.
 */
public class SynchBankTest {
    public static void main(String[] args) {
        final int ACCOUNTS_NUMBER = 100;
        final double INITIAL_BALANCE = 1000;
        final double MAX_AMOUNT = 1000;
        final int DELAY = 10;

        Bank bank = new Bank(ACCOUNTS_NUMBER, INITIAL_BALANCE);
        for (int i = 0; i < ACCOUNTS_NUMBER; i++) {
            int fromAccount = i;
            Runnable runnable = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (ACCOUNTS_NUMBER * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((long) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();
        }
    }
}
