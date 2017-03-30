package com.github.frankkwok.corejava.v1ch06.innerclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * This program demonstrates the inner class
 *
 * @author Frank Kwok on 2017/3/30.
 */
public class InnerClassTest {
    public static void main(String[] args) {
        TalkingClock talkingClock = new TalkingClock(3000, true);
        talkingClock.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock {
    private int interval;
    private boolean beep;

    TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    void start() {
        ActionListener listener = new TimePrinter();
        Timer timer = new Timer(interval, listener);
        timer.start();
    }

    class TimePrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At this tone, the time is " + new Date());
            if (beep) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
