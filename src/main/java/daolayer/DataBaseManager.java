package daolayer;

import execution.Start;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    private static DataBaseManager db;
    private Connection conn = null;

    private DataBaseManager(String driver, String url) {

        try {
            Class.forName(driver).newInstance();
            System.out.println("Driver loaded successfully ...");
        } catch (Exception e) {
            System.out.println("Can't load drivers ...");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Start.sysLogger.toLogSevere("Cant load driver !!! Current stack trace is:\n" + sw.toString());

        }
        try {
            System.out.println("Obtaining connection ...");
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            System.out.println("Connection successful!!!");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            Start.sysLogger.toLogSevere("SQL exception: " + ex.getMessage() + "\nCurrent stack trace is:\n" + sw.toString());
        }
    }

    public static DataBaseManager newInstance() {
        if (db == null) {
            db = new DataBaseManager(Start.MYSQL_DRIVER, Start.MYSQL_CONN_URL);
            System.out.println("DataBaseManagerObj was created and returned");
            return db;
        } else {
            System.out.println("DataBaseManagerObj was returned");
            return db;
        }
    }

    public void closeConnection() {
        if (conn != null) {
            System.out.println("Connection is not null, closing ...");
            try {
                conn.close();
                System.out.println("Connection is closed now...");
            } catch (SQLException e) {
                System.out.println("Unable to close connection");
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Start.sysLogger.toLogSevere("Unable to close connection!!! Current stack trace is:\n" + sw.toString());
            }
        } else {
            System.out.println("Connection is null, nothing to close");
        }
    }


    public Connection getConnection() {
        return conn;
    }

}
