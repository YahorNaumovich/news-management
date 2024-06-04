package edu.training.web.dao.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.dao.*;
import edu.training.web.service.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLAuthenticationDao implements AuthenticationDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public SQLAuthenticationDao() {
    }

    @Override
    public User signIn(AuthenticationInfo authenticationInfo) throws DaoException {

        String signInSql = "SELECT u.id, u.username, u.email, r.name FROM users u JOIN roles r ON u.Roles_id = r.id WHERE u.email = ? AND u.password = ?";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(signInSql)) {

            statement.setString(1, authenticationInfo.getLogin());
            statement.setString(2, authenticationInfo.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String roleName = resultSet.getString("name");
                    UserRoles role = UserRoles.valueOf(roleName.toUpperCase());

                    return new User(id, username, role);

                } else {
                    throw new DaoException("Invalid login or password");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error occurred during sign in", e);
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean userExists(String email) throws DaoException {

        String checkUserSql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(checkUserSql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }

                return false;

            }
        } catch (SQLException e) {
            throw new DaoException("Database error occurred while checking user existence", e);
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws DaoException {

        String insertUserSql = "INSERT INTO users (username, email, password, Roles_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(insertUserSql, PreparedStatement.RETURN_GENERATED_KEYS)) {



            statement.setString(1, userRegistrationInfo.getLogin());
            statement.setString(2, userRegistrationInfo.getEmail());
            statement.setString(3, userRegistrationInfo.getPassword());
            statement.setInt(4, 4);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new User(id, userRegistrationInfo.getLogin(), UserRoles.READER);
                } else {
                    throw new DaoException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {

            throw new DaoException("Database error occurred during sign up", e);

        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, User> getAllUsers() throws DaoException {

        Map<String, User> usersMap = new HashMap<>();

        String sql = "SELECT u.id, u.username, u.email, r.name AS role_name FROM users u INNER JOIN roles r ON u.Roles_id = r.id";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String roleName = resultSet.getString("role_name");
                    UserRoles role = UserRoles.valueOf(roleName.toUpperCase());

                    User user = new User(id, username, role);

                    usersMap.put(email, user);
                }
            }
        } catch (SQLException e) {

            throw new DaoException("Error retrieving users from the database", e);

        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
        return usersMap;
    }
}
