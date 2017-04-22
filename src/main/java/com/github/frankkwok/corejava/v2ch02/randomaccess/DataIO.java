package com.github.frankkwok.corejava.v2ch02.randomaccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Frank Kwok on 2017/4/22.
 */
class DataIO {
    static void writeFixedString(String s, int size, DataOutput output) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) {
                ch = s.charAt(i);
            }
            output.writeChar(ch);
        }
    }

    static String readFixedString(int size, DataInput input) throws IOException {
        StringBuilder b = new StringBuilder(size);
        int i = 0;
        boolean more = true;
        while (more && i < size) {
            char ch = input.readChar();
            i++;
            if (ch == 0) {
                more = false;
            } else {
                b.append(ch);
            }
        }
        input.skipBytes(2 * (size - i));
        return b.toString();
    }
}
