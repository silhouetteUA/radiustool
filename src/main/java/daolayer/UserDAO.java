package daolayer;

import models.User;

import java.util.List;

public interface UserDAO {
    List<User> getProvisionedUsers();
}
