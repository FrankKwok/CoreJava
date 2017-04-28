package com.github.frankkwok.corejava.v2ch04.urlconnection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/4/28.
 */
public class URLConnectionTest {
    public static void main(String[] args) throws IOException {
        String urlString;
        if (args.length > 0) {
            urlString = args[0];
        } else {
            urlString = "http://baidu.com";
        }

        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();

        if (args.length > 2) {
            String username = args[1];
            String password = args[2];
            String input = username + ":" + password;
            Base64.Encoder encoder = Base64.getEncoder();
            String encoded = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
            urlConnection.setRequestProperty("Authorization", encoded);
        }

        urlConnection.connect();

        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                System.out.println(key + ":" + value);
            }
        }

        System.out.println("----------");
        System.out.println("getContentType: " + urlConnection.getContentType());
        System.out.println("getContentLength: " + urlConnection.getContentLength());
        System.out.println("getContentEncoding: " + urlConnection.getContentEncoding());
        System.out.println("getDate: " + urlConnection.getDate());
        System.out.println("getExpiration： " + urlConnection.getExpiration());
        System.out.println("getLastModified: " + urlConnection.getLastModified());
        System.out.println("----------");

        String encoding = urlConnection.getContentEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }

        try (Scanner in = new Scanner(new InputStreamReader(urlConnection.getInputStream(), encoding))) {
            for (int i = 0; i < 10 && in.hasNextLine(); i++) {
                System.out.println(in.nextLine());
            }
            if (in.hasNextLine()) {
                System.out.println("...");
            }
        }
    }
}
