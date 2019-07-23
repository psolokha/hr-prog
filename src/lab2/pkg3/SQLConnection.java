package lab2.pkg3;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;


public class SQLConnection {
    
    public static final String DRV_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String CONN_STRING = "jdbc:mysql://localhost:3306/?user=root&password=root&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Connection conn = null;
    
    public static void driverTest(){
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) System.out.println(drivers.nextElement());
    }
    
    public static Connection sqlConnect(){
        try {
            conn = DriverManager.getConnection(CONN_STRING);
        } catch (SQLException ex) {
            System.out.println("Cannot open connection" + ex.getMessage());
            return null;
        }
        return conn;
    }
    

    
}
