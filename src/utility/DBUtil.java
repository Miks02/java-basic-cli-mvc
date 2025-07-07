package utility;

import java.sql.*;

public class DBUtil {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement", "root", "");
    }

}
