package com.github.frankkwok.corejava.v2ch04.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/4/28.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(11269)) {
            try (Socket socket = serverSocket.accept()) {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                try (Scanner in = new Scanner(inputStream, "UTF-8");
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true)) {
                    out.println("Hello my friend, welcome to echo! If you want to leave, please enter BYE to exit.");

                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        System.out.println("Client: " + line);
                        String reply = "Echo " + line;
                        out.println(reply);
                        System.out.println(reply);
                        if (line.trim().equalsIgnoreCase("BYE")) {
                            done = true;
                        }
                    }
                }
            }
        }
    }
}
