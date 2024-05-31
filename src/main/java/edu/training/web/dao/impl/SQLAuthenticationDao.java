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
    public boolean userExists(String email) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String checkUserSql = "SELECT COUNT(*) FROM users WHERE email = ?";
            statement = connection.prepareStatement(checkUserSql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new DaoException("Database error occurred while checking user existence", e);
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
    }


    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws DaoException {

        PreparedStatement statement = null;

        try {

            // Insert the new user into the database
            String insertUserSql = "INSERT INTO users (username, email, password, Roles_id) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(insertUserSql);
            statement.setString(1, userRegistrationInfo.getLogin());
            statement.setString(2, userRegistrationInfo.getEmail());
            statement.setString(3, userRegistrationInfo.getPassword());
            statement.setInt(4, 4);
            statement.executeUpdate();

            // Return the new user object
            return new User(userRegistrationInfo.getLogin(), UserRoles.READER);

        } catch (SQLException e) {
            throw new DaoException("Database error occurred during sign up", e);
        } finally {
            // Close resources
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Handle exception
                }
            }
        }
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
