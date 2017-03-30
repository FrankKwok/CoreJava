package com.github.frankkwok.corejava.v1ch06.timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * implements the ActionListener interface
 *
 * @author Frank Kwok on 2017/3/30.
 */
public class TimerTest {
    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        Timer timer = new Timer(3000, listener);
        timer.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At this tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}
