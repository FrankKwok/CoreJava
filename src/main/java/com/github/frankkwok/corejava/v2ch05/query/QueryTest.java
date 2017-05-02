package com.github.frankkwok.corejava.v2ch05.query;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Frank Kwok on 2017/5/2.
 */
public class QueryTest {
    private static final String ALL_QUERY = "SELECT Books.Price, Books.Title FROM Books";
    private static final String AUTHOR_PUBLISHER_QUERY = "SELECT Books.Price, Books.Title" +
            " FROM Books, BooksAuthors, Authors, Publishers" +
            " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN" +
            " AND Books.Publisher_Id = Publishers.Publisher_Id AND Authors.Name = ?" +
            " AND Publishers.Name = ?";
    private static final String AUTHOR_QUERY = "SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors" +
            " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN" +
            " ND Authors.Name = ?";
    private static final String PUBLISHER_QUERY = "SELECT Books.Price, Books.Title FROM Books, Publishers" +
            " WHERE Books.Publisher_Id = Publishers.Publisher_Id AND Publishers.Name = ?";
    private static final String PRICE_UPDATE = "UPDATE Books" +
            " SET Price = Price + ?" +
            " WHERE Books.Publisher_Id = (SELECT Publisher_Id FROM Publishers WHERE Name = ?)";

    private static Scanner in;
    private static List<String> authors = new ArrayList<>();
    private static List<String> publishers = new ArrayList<>();

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            in = new Scanner(System.in);
            authors.add("Any");
            publishers.add("Any");
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT Name FROM Authors";
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        authors.add(resultSet.getString(1));
                    }
                }

                query = "SELECT Name FROM Publishers";
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        publishers.add(resultSet.getString(1));
                    }
                }
            }
            boolean done = false;
            while (!done) {
                System.out.println("Q)uery C)hange prices E)xit: ");
                String input = in.next().toUpperCase();
                if (input.equals("Q")) {
                    executeQuery(connection);
                } else if (input.equals("C")) {
                    changePrices(connection);
                } else {
                    done = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeQuery(Connection connection) throws SQLException {
        String author = select("Authors:", authors);
        String publisher = select("Publishers:", publishers);
        PreparedStatement stat;
        if (!author.equals("Any") && !publisher.equals("Any")) {
            stat = connection.prepareStatement(AUTHOR_PUBLISHER_QUERY);
            stat.setString(1, author);
            stat.setString(2, publisher);
        } else if (!author.equals("Any") && publisher.equals("Any")) {
            stat = connection.prepareStatement(AUTHOR_QUERY);
            stat.setString(1, author);
        } else if (author.equals("Any") && !publisher.equals("Any")) {
            stat = connection.prepareStatement(PUBLISHER_QUERY);
            stat.setString(1, publisher);
        } else
            stat = connection.prepareStatement(ALL_QUERY);

        try (ResultSet rs = stat.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getString(1) + ", " + rs.getString(2));
        }
    }

    private static void changePrices(Connection connection) throws SQLException {
        String publisher = select("Publishers:", publishers.subList(1, publishers.size()));
        System.out.print("Change prices by: ");
        double priceChange = in.nextDouble();
        PreparedStatement stat = connection.prepareStatement(PRICE_UPDATE);
        stat.setDouble(1, priceChange);
        stat.setString(2, publisher);
        int r = stat.executeUpdate();
        System.out.println(r + " records updated.");
    }

    private static String select(String prompt, List<String> options) {
        while (true) {
            System.out.println(prompt);
            for (int i = 0; i < options.size(); i++)
                System.out.printf("%2d) %s%n", i + 1, options.get(i));
            int sel = in.nextInt();
            if (sel > 0 && sel <= options.size())
                return options.get(sel - 1);
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
