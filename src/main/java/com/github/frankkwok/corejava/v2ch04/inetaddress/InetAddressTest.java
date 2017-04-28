package com.github.frankkwok.corejava.v2ch04.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Frank Kwok on 2017/4/28.
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        if (args.length > 0) {
            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress ia : addresses) {
                System.out.println(ia);
            }
        } else {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
