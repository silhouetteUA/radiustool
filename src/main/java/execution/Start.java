package execution;

import daolayer.ConnectionFactory;
import daolayer.MySqlFactory;
import daolayer.UserDAO;
import logging.SysLogger;
import models.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Start {

    public static String MYSQL_DRIVER;
    public static String MYSQL_CONN_URL;
    public static SysLogger sysLogger;
    private static String logPath;
    private static String logSize;
    private static String logCount;

    public static void main(String[] args) {

        // READ configuration file
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("radconfig.txt");
            properties.load(fileInputStream);
            MYSQL_DRIVER = properties.getProperty("MYSQL_DRIVER").trim();
            MYSQL_CONN_URL = properties.getProperty("MYSQL_CONN_URL").trim();
            logPath = properties.getProperty("log_path").trim();
            logSize = properties.getProperty("log_size").trim();
            logCount = properties.getProperty("log_count").trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Initialize LOGGER
        try {
            Handler handler = new FileHandler(logPath, Integer.parseInt(logSize), Integer.parseInt(logCount), true);
            Logger logger = Logger.getLogger(Start.class.getName());
            sysLogger = new SysLogger(handler, logger);
        } catch (SecurityException | IOException e) {
            System.out.println("Unable to initialize logger");
        }


        //Start tool execution, get provisioning data from 'radius.provisioning'
        sysLogger.toLogInfo("Execution started ...");
        ConnectionFactory connectionFactory = new MySqlFactory();
        UserDAO userDAO = connectionFactory.getUserDAO();
        List<User> provisionList = userDAO.getProvisionedUsers();

        if (provisionList.size() != 0) {
            for (User provisionedUser : provisionList) {
                sysLogger.toLogInfo(provisionedUser.toString());
            }
            //Send shell commands towards BRAS/BNG (CoA, Disconnect etc.)
            SendShellCommand sendShellCommand = new SendShellCommand();
            sendShellCommand.executeCommandList(provisionList);
            sysLogger.toLogInfo("Execution ended ...");
        } else {
            sysLogger.toLogInfo("Nothing to provision, Execution ended!");
        }


    }
}
