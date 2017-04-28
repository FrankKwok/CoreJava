package com.github.frankkwok.corejava.v2ch04.threaded;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/4/28.
 */
public class ThreadedEchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(11269)) {
            int i = 1;
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accept " + i);
                Runnable runnable = new ThreadedEchoHandler(socket, i);
                Thread thread = new Thread(runnable);
                thread.start();
                i++;
            }
        }
    }
}

class ThreadedEchoHandler implements Runnable {
    private Socket socket;
    private int i;

    ThreadedEchoHandler(Socket socket, int i) {
        this.socket = socket;
        this.i = i;
    }

    @Override
    public void run() {
        try (Scanner in = new Scanner(socket.getInputStream(), "UTF-8");
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {
            out.println("Hello my friend, welcome to threaded echo! If you want to leave, please enter BYE to exit.");

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println("Client " + i + ": " + line);
                String reply = "Echo " + line;
                out.println(reply);
                System.out.println(reply);
                if (line.trim().equalsIgnoreCase("BYE")) {
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
