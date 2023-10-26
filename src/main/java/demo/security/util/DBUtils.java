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
//        String query = "SELECT userid FROM users WHERE username = '" + user  + "' AND password='" + pass + "'";
        String query = "SELECT userid FROM users WHERE user = ? AND pass = ?";

//        Statement statement = connection.createStatement();
        List<String> users = new ArrayList<String>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user);
            statement.setString(2, pass);

            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                users.add(resultSet.getString(0));
            }
        }
        return users;
    }

}
