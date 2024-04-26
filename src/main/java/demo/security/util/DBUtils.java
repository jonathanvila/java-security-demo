package demo.security.util;

import demo.security.servlet.FileServlet;

import javax.naming.AuthenticationException;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

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

    public void threads() {
        Thread.Builder.OfVirtual virtual = Thread.ofVirtual();

        var thread = virtual.start(() -> {
          var a=10;
          System.out.println(a);
        });
        thread.setDaemon(false);
        thread.setPriority(10);
        thread.getThreadGroup();
        System.out.print(thread.getName());

        Runnable printThread = () -> System.out.println(Thread.currentThread());

        Thread virtualThread = Thread.ofVirtual().factory().newThread(printThread);

        ThreadFactory kernelThreadFactory = Thread.ofPlatform().factory();
        Thread kernelThread = kernelThreadFactory.newThread(printThread);

        virtualThread.setPriority(Thread.MIN_PRIORITY);
        virtualThread.start();
        kernelThread.start();

        var kernelThread3 = new Thread(printThread);
        kernelThread3.setPriority(0);
        kernelThread3.setDaemon(false);
        System.out.println(STR."Group:\{kernelThread3.getThreadGroup()}");
        kernelThread3.start();
    }

    String guardedCaseSwitch(Object obj) {
        return switch (obj) {
            case String s when !s.isEmpty() -> String.format("String %s", s);
            case Integer i when i > 0 -> String.format("int %d", i);
            default        -> obj.toString();
        };
    }

    public double calculateAverage(Collection<Integer> collection) {
        int suma= 10;  

        int sum = 0;
        for (Integer num : collection) {
            sum += num;
        }
        return (double) sum / collection.size();
    }

    public double calculateAverageManual(Collection<Integer> collection) {
        int suma = 10;
        return collection.stream().collect(Collectors.teeing(
                Collectors.summingDouble(i -> i),
                Collectors.counting(),
                (sum, count) -> sum / count));
    }
}
