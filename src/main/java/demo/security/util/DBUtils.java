package demo.security.util;

import demo.security.servlet.FileServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    Object value;

    Connection connection;


    /**
     * The following code shows how to use {@code Optional.isPresent}:
     * {@snippet :
     * if (v.isPresent()) { // @highlight substring="isPresent"
     * System.out.println("v: " + v.get());
     * }
     *}
     * Where v != null
     */
    public boolean isPresent() {
        return value != null;
    }

    public DBUtils() throws SQLException {
        connection = DriverManager.getConnection(
                "mYJDBCUrl", "myJDBCUser", "myJDBCPasswd");

        ArrayList<String> list = new ArrayList<>();
        list.add("hola");
        for (var it = list.listIterator(list.size()); it.hasPrevious();) {
            var element = it.previous();
            System.out.println(element);
        }
    }

    public void newConnect() throws SQLException {
        connection = DriverManager.getConnection(
                "mYJDBCUrl", "myJDBCUser", "myJDBCPasswd");

        ArrayList<String> list = new ArrayList<>();
        list.add("hola");
        for (var it = list.listIterator(list.size()); it.hasPrevious();) {
            var element = it.previous();
            System.out.println(element);
        }
    }

    public List<String> findUsers(String user) throws Exception {
        String query = "SELECT userid FROM users WHERE username = '" + user + "'";
        var myvar = 42;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> users = new ArrayList<String>();
        while (resultSet.next()) {
            users.add(resultSet.getString(0));
        }
        sumNumbers(100);
        return users;
    }

    public List<String> findItem(String itemId) throws Exception {
        //TODO let's do something
        String query = "SELECT item_id FROM items WHERE item_id = '" + itemId + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> items = new ArrayList<String>();
        while (resultSet.next()) {
            items.add(resultSet.getString(0));
        }
        return items;
    }

    public void sumNumbers(int max) {
        int count, sum = 0;
        for (int number = 1; number <= max; number++) {
            count = 0;
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    for (int m = 2; i <= number / 2; i++) {
                        if (number % m == 0) {
                            count++;
                            break;
                        }
                    }
                    count++;
                    break;
                }
            }
            if (count == 0 && number != 1) {
                sum = sum + number;
            }
        }
        System.out.println("The Sum of numbers is: " + sum);
    }

    /**
     * Computes the sum of all prime numbers up to max using Java Streams.
     * @param max the upper bound
     * @return the sum of all prime numbers up to max
     */
    public static int sumNumbersStream(int max) {
        return java.util.stream.IntStream.rangeClosed(2, max)
            .filter(n -> java.util.stream.IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .allMatch(i -> n % i != 0))
            .sum();
    }


    /**
     * Returns the sum of all prime numbers up to max using the original logic (without printing).
     * @param max the upper bound
     * @return the sum of all prime numbers up to max
     */
    public static int getSumNumbers(int max) {
        int count, sum = 0;
        for (int number = 1; number <= max; number++) {
            count = 0;
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    for (int m = 2; i <= number / 2; i++) {
                        if (number % m == 0) {
                            count++;
                            break;
                        }
                    }
                    count++;
                    break;
                }
            }
            if (count == 0 && number != 1) {
                sum = sum + number;
            }
        }
        return sum;
    }

    public void sumNumbersRefactored(int max) {
        int sum = 0;
        for (int number = 2; number <= max; number++) {
            if (isPrime(number)) {
                sum += number;
            }
        }
        System.out.println("The Sum of numbers is: " + sum);
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
