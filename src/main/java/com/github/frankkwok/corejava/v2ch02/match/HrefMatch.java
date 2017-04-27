package com.github.frankkwok.corejava.v2ch02.match;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Frank Kwok on 2017/4/27.
 */
public class HrefMatch {
    public static void main(String[] args) throws IOException {
        String urlString;
        if (args.length > 0) {
            urlString = args[0];
        } else {
            urlString = "https://www.baidu.com";
        }

        InputStreamReader in = new InputStreamReader(new URL(urlString).openStream(), StandardCharsets.UTF_8);

        StringBuilder builder = new StringBuilder();
        int ch;
        while ((ch = in.read()) != -1) {
            builder.append((char) ch);
        }

        String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(builder);

        while (matcher.find()) {
            String match = matcher.group();
            System.out.println(match);
        }
    }
}
