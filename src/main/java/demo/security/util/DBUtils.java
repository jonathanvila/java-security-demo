package demo.security.util;

import demo.security.servlet.FileServlet;

import java.net.HttpURLConnection;
import java.net.URL;
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
     * Connects to the given external URL 1000 times using threads.
     * Each thread performs a single connection and logs the response code.
     *
     * @param urlString the external URL to connect to
     */
    public static void connectToExternalUrlConcurrently(String urlString) {
        final int THREAD_COUNT = 1000;
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = Thread.ofVirtual().unstarted(() -> {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    conn.disconnect();
                } catch (Exception e) {
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
