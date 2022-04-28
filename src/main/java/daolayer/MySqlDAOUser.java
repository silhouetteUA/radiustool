package daolayer;

import execution.Start;
import models.User;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDAOUser implements UserDAO {

    private DataBaseManager dataBaseManager;
    private List<User> privisionedUsers;

    public MySqlDAOUser(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public List<User> getProvisionedUsers() {
        privisionedUsers = new ArrayList<>();
        final String selectProvisionedUsers = "select * from provisioning where provisioning_time >= DATE_SUB(NOW(), interval 1 hour)";
        try {
            PreparedStatement pst = null;
            System.out.println("DBM:" + dataBaseManager);
            Connection conn = dataBaseManager.getConnection();
            System.out.println("Connection: " + conn);
            pst = conn.prepareStatement(selectProvisionedUsers);
            System.out.println("Obtained statement");
            try {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    int profile = rs.getInt("profile");
                    String sessionid = rs.getString("session_id");
                    user.setId(id);
                    user.setUsername(username);
                    user.setProfile(profile);
                    user.setSessionId(sessionid);
                    if (!user.getSessionId().equals("notactive")) {
                        privisionedUsers.add(user);
                    }

                }
                rs.close();
            } catch (SQLException e) {
                System.out.println("Query Select Error");
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Start.sysLogger.toLogSevere("Query Select Error!!! Current stack trace is:\n" + sw.toString());
            } finally {
                pst.close();
            }
        } catch (SQLException e) {
            System.out.println("Statement error!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Start.sysLogger.toLogSevere("Statement error !!! Current stack trace is:\n" + sw.toString());
        }
        return privisionedUsers;
    }
}
