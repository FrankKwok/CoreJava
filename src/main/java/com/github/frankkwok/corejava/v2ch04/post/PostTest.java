package com.github.frankkwok.corejava.v2ch04.post;

import com.github.frankkwok.corejava.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/4/28.
 */
public class PostTest {
    public static void main(String[] args) throws IOException {
        String propsFilename;
        if (args.length > 0) {
            propsFilename = args[0];
        } else {
            propsFilename = "post.properties";
        }

        Properties properties = new Properties();
        try (InputStream in = ResourceUtils.newInputStream(propsFilename)) {
            properties.load(in);
        }

        String urlString = properties.remove("url").toString();
        Object userAgent = properties.remove("User-Agent");
        Object redirects = properties.remove("redirects");
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String result = doPost(new URL(urlString), properties,
                userAgent == null ? null : userAgent.toString(),
                redirects == null ? -1 : Integer.parseInt(redirects.toString()));
        System.out.println(result);
    }

    private static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent, int redirects) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (userAgent != null) {
            connection.setRequestProperty("User-Agent", userAgent);
        }
        if (redirects >= 0) {
            connection.setInstanceFollowRedirects(false);
        }
        connection.setDoOutput(true);

        try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
            boolean first = true;
            for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    out.print('&');
                }
                String key = pair.getKey().toString();
                String value = pair.getValue().toString();
                out.print(key);
                out.print('=');
                out.print(URLEncoder.encode(value, "UTF-8"));
            }
        }

        String encoding = connection.getContentEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }

        if (redirects > 0) {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
                    responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
                    responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
                }
            }
        } else if (redirects == 0) {
            throw new IOException("Too many redirects.");
        }

        StringBuilder response = new StringBuilder();
        try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
        } catch (IOException exception) {
            InputStream err = connection.getErrorStream();
            if (err == null) {
                throw exception;
            }
            try (Scanner in = new Scanner(err, encoding)) {
                while (in.hasNextLine()) {
                    response.append(in.nextLine());
                    response.append("\n");
                }
            }
        }
        return response.toString();
    }
}
