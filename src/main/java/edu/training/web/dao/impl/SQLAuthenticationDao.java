package edu.training.web.dao.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.dao.AuthenticationDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.SQLBaseDao;
import edu.training.web.service.UserRoles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLAuthenticationDao extends SQLBaseDao implements AuthenticationDao {

    HashMap<String, User> users = new HashMap<>();

    public SQLAuthenticationDao() {
        users.put("admin@mail.ru", new User("Admin", UserRoles.ADMINISTRATOR));
        users.put("contributor@mail.ru", new User("Contributor", UserRoles.CONTRIBUTOR));
        users.put("moderator@mail.ru", new User("Moderator", UserRoles.MODERATOR));
        users.put("reader@mail.ru", new User("Reader", UserRoles.READER));
    }

    @Override
    public User signIn(AuthenticationInfo authenticationInfo) throws DaoException {
        return users.get(authenticationInfo.getLogin());
    }

    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws DaoException {

        if (users.containsKey(userRegistrationInfo.getEmail())) {
            throw new DaoException("This email is already registered");
        }

        if (!userRegistrationInfo.getPassword().equals(userRegistrationInfo.getConfirmPassword())) {
            throw new DaoException("Passwords do not match");
        }

        users.put(userRegistrationInfo.getEmail(), new User(userRegistrationInfo.getLogin(), UserRoles.READER));

        return new User(userRegistrationInfo.getLogin(), UserRoles.READER);
    }

    @Override
    public Map<String, User> getAllUsers() throws DaoException {

        Map<String, User> usersMap = new HashMap<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT u.id, u.username, u.email, r.name AS role_name FROM users u INNER JOIN roles r ON u.Roles_id = r.id";

            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String roleName = resultSet.getString("role_name");
                UserRoles role = UserRoles.valueOf(roleName.toUpperCase());

                User user = new User(username, role);

                usersMap.put(email, user);
            }

        } catch (SQLException e) {
            throw new DaoException("Error retrieving users from the database", e);
        } finally {
            // Close resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // Handle exception
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Handle exception
                }
            }
        }
        return usersMap;
    }
}
