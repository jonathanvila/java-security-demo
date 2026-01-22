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





}
