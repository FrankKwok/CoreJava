package com.github.frankkwok.corejava.v2ch04.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/4/27.
 */
public class EchoClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 11269);
             Scanner in = new Scanner(socket.getInputStream(), "UTF-8");
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
             Scanner scanner = new Scanner(System.in)) {

            boolean done = false;
            while (!done && in.hasNextLine()) {
                System.out.println("EchoServer: " + in.nextLine());
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    out.println(line);
                    if (line.equalsIgnoreCase("BYE")) {
                        done = true;
                    }
                }
            }
        }
    }
}
