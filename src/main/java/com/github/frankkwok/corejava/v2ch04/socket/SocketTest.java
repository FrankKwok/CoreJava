package com.github.frankkwok.corejava.v2ch04.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/4/27.
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("s2a.time.edu.cn", 123);
             Scanner in = new Scanner(socket.getInputStream(), "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }
}
