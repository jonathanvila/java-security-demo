package demo.security.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    Connection connection;
    public DBUtils() throws SQLException {
        connection = DriverManager.getConnection(
                "mYJDBCUrl", "myJDBCUser", "hardcodedJDBCPasswd");
    }

    public List<String> findUsers(String user, String pass) throws Exception {
        String query = "SELECT userid FROM users WHERE username = '" + user  + "' AND password='" + pass + "'";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> users = new ArrayList<String>();
        while (resultSet.next()){
            users.add(resultSet.getString(0));
        }
        return users;
    }

}
