package java.concurrency.practice.three.three;

import java.sql.Connection;
import java.sql.DriverManager;

public class ThreadLocalHolder {
    private static ThreadLocal<Connection> connectionHolder
            = new ThreadLocal<Connection>() {
        public Connection initialValue() {
            return DriverManager.getConnection(DB_URL);
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
