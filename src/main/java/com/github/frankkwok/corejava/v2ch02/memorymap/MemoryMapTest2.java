package com.github.frankkwok.corejava.v2ch02.memorymap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * @author Frank Kwok on 2017/4/27.
 */
public class MemoryMapTest2 {
    private static final int BLOCK_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        System.out.println("InputStream:");
        Path filename = Paths.get(args[0]);
        long start = System.currentTimeMillis();
        long crcValue = checksumInputStream(filename);
        long end = System.currentTimeMillis();
        System.out.printf("crcValue: %h, time cost: %d ms%n%n", crcValue, (end - start));

        System.out.println("BufferedInputStream:");
        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.printf("crcValue: %h, time cost: %d ms%n%n", crcValue, (end - start));

        System.out.println("RandomAccessFile:");
        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.printf("crcValue: %h, time cost: %d ms%n%n", crcValue, (end - start));

        System.out.println("MappedFile:");
        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.printf("crcValue: %h, time cost: %d ms%n%n", crcValue, (end - start));
    }

    private static long checksumInputStream(Path filename) throws IOException {
        try (InputStream in = Files.newInputStream(filename)) {
            CRC32 crc32 = new CRC32();

            byte[] bytes = new byte[BLOCK_SIZE];
            int len;
            while ((len = in.read(bytes)) != -1) {
                crc32.update(bytes, 0, len);
            }
            return crc32.getValue();
        }
    }

    private static long checksumBufferedInputStream(Path filename) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(filename))) {
            CRC32 crc32 = new CRC32();

            byte[] bytes = new byte[BLOCK_SIZE];
            int len;
            while ((len = in.read(bytes)) != -1) {
                crc32.update(bytes, 0, len);
            }
            return crc32.getValue();
        }
    }

    private static long checksumRandomAccessFile(Path filename) throws IOException {
        try (RandomAccessFile in = new RandomAccessFile(filename.toFile(), "r")) {
            CRC32 crc32 = new CRC32();
            byte[] bytes = new byte[BLOCK_SIZE];

            /*int len;
            while ((len = in.read(bytes)) != -1) {
                crc32.update(bytes, 0, len);
            }*/

            long length = in.length();
            for (int i = 0; i < length; i += BLOCK_SIZE) {
                in.seek(i);
                int len = in.read(bytes);
                crc32.update(bytes, 0, len);
            }
            return crc32.getValue();
        }
    }

    private static long checksumMappedFile(Path filename) throws IOException {
        try (FileChannel in = FileChannel.open(filename)) {
            CRC32 crc32 = new CRC32();

            long length = in.size();
            MappedByteBuffer buffer = in.map(FileChannel.MapMode.READ_ONLY, 0, length);
            byte[] bytes = new byte[BLOCK_SIZE];

            for (int i = 0; i < length; i += BLOCK_SIZE) {
                int len = (int) Math.min(bytes.length, length - i);
                buffer.get(bytes, 0, len);
                crc32.update(bytes, 0, len);
            }
            return crc32.getValue();
        }
    }
}
