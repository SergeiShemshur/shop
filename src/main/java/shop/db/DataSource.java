package shop.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String url = "jdbc:mysql://localhost:3306/shop";
    private String user = "root";
    private String password = "1107";
    public static Connection connection;
    private static DataSource dataSource;

    private DataSource() {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
           connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DataSource getDataSource() {
        if ( dataSource == null ) {
            dataSource = new DataSource();
        }
       return dataSource;
    }


    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
