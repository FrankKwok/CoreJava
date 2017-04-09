package com.github.frankkwok.corejava.v1ch14.unsynch;

/**
 * @author Frank Kwok on 2017/4/9.
 */
public class UnsynchBankTest {
    public static void main(String[] args) {
        final int ACCOUNT_NUMBER = 100;
        final double INITIAL_BALANCE = 1000;
        final double MAX_AMOUNT = 1000;
        final int DELAY = 10;

        Bank bank = new Bank(ACCOUNT_NUMBER, INITIAL_BALANCE);
        for (int i = 0; i < ACCOUNT_NUMBER; i++) {
            int fromAccount = i;
            Runnable runnable = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
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
