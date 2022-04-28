package daolayer;

public class MySqlFactory extends ConnectionFactory {

    private DataBaseManager dataBaseManager;

    public MySqlFactory() {
        dataBaseManager = DataBaseManager.newInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        MySqlDAOUser userDAO = new MySqlDAOUser(dataBaseManager);
        return userDAO;
    }
}
