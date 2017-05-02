package com.github.frankkwok.corejava.v2ch05.exec;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/5/2.
 */
public class ExecSQL {
    public static void main(String[] args) throws IOException, SQLException {
        try (Scanner in = (args.length == 0 ? new Scanner(System.in) : new Scanner(Paths.get(args[0]), "UTF-8"))) {
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {
                while (true) {
                    if (args.length == 0) {
                        System.out.println("Enter command or EXIT to exit: ");
                    }
                    if (!in.hasNextLine()) {
                        return;
                    }

                    String line = in.nextLine().trim();
                    if (line.equalsIgnoreCase("EXIT")) {
                        return;
                    }
                    if (line.endsWith(";")) {
                        line = line.substring(0, line.length() - 1);
                    }
                    boolean isResult = statement.execute(line);
                    if (isResult) {
                        try (ResultSet resultSet = statement.getResultSet()) {
                            showResultSet(resultSet);
                        }
                    } else {
                        int updateCount = statement.getUpdateCount();
                        System.out.println(updateCount + " rows updated");
                    }
                }
            }
        }
    }

    private static void showResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                System.out.print(",");
            }
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print(",");
                }
                System.out.print(resultSet.getString(i));
            }
            System.out.println();
        }
    }

    private static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(in);
        }

        String drivers = properties.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }

        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }
}
