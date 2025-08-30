package vn.iostar.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBconnect {

    private final String serverName = "localhost";
    private final String dbName = "BTC3";
    private final String portNumber = "1433";
    private final String instance = "";   // SQL Server instance name (nếu có)
    private final String userID = "sa";
    private final String password = "hoang1808";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
                   + ";encrypt=true;trustServerCertificate=true;databaseName=" + dbName;

        // Nếu không có instance thì bỏ qua
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";encrypt=true;trustServerCertificate=true;databaseName=" + dbName;
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    public static void main(String[] args) {
        try (Connection conn = new DBconnect().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [User]")) {

            System.out.println("ID | Email | Username | FullName | Password | Avatar | RoleID | Phone | CreatedDate");
            System.out.println("-----------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String userName = rs.getString("userName");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String avatar = rs.getString("avatar");
                int roleId = rs.getInt("roleid");
                String phone = rs.getString("phone");
                java.sql.Timestamp createdDate = rs.getTimestamp("createdDate");

                System.out.printf(
                    "%d | %s | %s | %s | %s | %s | %d | %s | %s%n",
                    id, email, userName, fullName, password, avatar, roleId, phone, createdDate
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
