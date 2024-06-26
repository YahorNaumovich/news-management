package edu.training.web.dao.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.dao.*;
import edu.training.web.dao.connectionpool.ConnectionPool;
import edu.training.web.dao.connectionpool.ConnectionPoolException;
import edu.training.web.service.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLAuthenticationDao implements AuthenticationDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger LOGGER = Logger.getLogger(SQLAuthenticationDao.class.getName());

    public SQLAuthenticationDao() {
    }

    private static final String SIGN_IN_SQL = "SELECT u.id, u.username, u.email, r.name FROM users u JOIN roles r ON u.Roles_id = r.id WHERE u.email = ? AND u.password = ?";

    @Override
    public User signInUser(AuthenticationInfo authenticationInfo) throws DaoException {

        LOGGER.log(Level.INFO, "Method signInUser is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SIGN_IN_SQL)) {

            statement.setString(1, authenticationInfo.getLogin());
            statement.setString(2, authenticationInfo.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {

                if (!resultSet.next()) {
                    LOGGER.log(Level.INFO, "Invalid login or password");
                    throw new DaoException("Invalid login or password");
                }

                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String roleName = resultSet.getString("name");
                UserRoles role = UserRoles.valueOf(roleName.toUpperCase());

                LOGGER.log(Level.INFO, "User {0} successfully signed in", username);

                return new User(id, username, role);

            }
        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.SEVERE, "Error occurred during sign in", e);
            throw new DaoException("Error occurred during sign in", e);

        }
    }

    private static final String CHECK_USER_EXISTS_SQL = "SELECT COUNT(*) FROM users WHERE email = ?";

    @Override
    public boolean userExists(String email) throws DaoException {

        LOGGER.log(Level.INFO, "Method userExists is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_USER_EXISTS_SQL)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        LOGGER.log(Level.INFO, "User {0} exists", email);
                        return true;
                    }

                }

            }

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.SEVERE, "Error occurred while checking user existence", e);
            throw new DaoException("Error occurred while checking user existence", e);

        }

        return false;
    }


    private static final String INSERT_USER_SQL = "INSERT INTO users (username, email, password, Roles_id) VALUES (?, ?, ?, ?)";

    @Override
    public User signUpUser(UserRegistrationInfo userRegistrationInfo) throws DaoException {

        LOGGER.log(Level.INFO, "Method signUpUser is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, userRegistrationInfo.getLogin());
            statement.setString(2, userRegistrationInfo.getEmail());
            statement.setString(3, userRegistrationInfo.getPassword());
            statement.setInt(4, 4);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {

                LOGGER.log(Level.INFO, "Creating user failed, no rows affected.");
                throw new DaoException("Creating user failed, no rows affected.");

            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    int id = generatedKeys.getInt(1);
                    LOGGER.log(Level.INFO, "User {0} successfully created", userRegistrationInfo.getLogin());
                    return new User(id, userRegistrationInfo.getLogin(), UserRoles.READER);

                } else {

                    LOGGER.log(Level.INFO, "Creating user failed, no ID obtained.");
                    throw new DaoException("Creating user failed, no ID obtained.");

                }
            }

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.SEVERE, "Error occurred during sign up", e);
            throw new DaoException("Error occurred during sign up", e);

        }
    }

    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";

    @Override
    public void deleteUser(int id) throws DaoException {

        LOGGER.log(Level.INFO, "Method deleteUser is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {

            statement.setInt(1, id);
            statement.executeUpdate();

            LOGGER.log(Level.INFO, "User {0} successfully deleted", id);

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.SEVERE, "Error occurred during deleting user", e);
            throw new DaoException("Error occurred during deleting user", e);

        }
    }

    private static final String CHANGE_USER_ROLE_SQL = "UPDATE users SET Roles_id = ? WHERE id = ?";

    @Override
    public void changeUserRole(int userId, int roleId) throws DaoException {

        LOGGER.log(Level.INFO, "Method changeUserRole is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_ROLE_SQL)) {

            statement.setInt(1, roleId);
            statement.setInt(2, userId);
            statement.executeUpdate();

            LOGGER.log(Level.INFO, "User {0} successfully changed role to {1}", new Object[] {userId, roleId});

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.SEVERE, "Error occurred during changing user role", e);
            throw new DaoException("Error occurred during changing user role", e);

        }
    }

    private static final String GET_ROLE_ID = "SELECT id FROM roles WHERE name = ?";

    @Override
    public int getUserRoleId(String roleName) throws DaoException {

        LOGGER.log(Level.INFO, "Method getUserRoleId is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ROLE_ID)) {

            statement.setString(1, roleName);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    LOGGER.log(Level.INFO, "Role {0} found", roleName);
                    return resultSet.getInt("id");

                } else {

                    LOGGER.log(Level.INFO, "Role {0} not found", roleName);
                    throw new SQLException("Role not found: " + roleName);

                }
            }

        } catch (ConnectionPoolException | SQLException e) {

            LOGGER.log(Level.SEVERE, "Error occurred during getting role id", e);
            throw new DaoException("Error occurred during getting role id", e);

        }
    }

    private static final String CHANGE_USER_PASSWORD_SQL = "UPDATE users SET password = ? WHERE id = ?";

    @Override
    public void changeUserPassword(int id, String newPassword) throws DaoException {

        LOGGER.log(Level.INFO, "Method changeUserPassword is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_PASSWORD_SQL)) {

            statement.setString(1, newPassword);
            statement.setInt(2, id);
            statement.executeUpdate();

            LOGGER.log(Level.INFO, "User {0} successfully changed password", id);

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.SEVERE, "Error occurred during changing user password", e);
            throw new DaoException("Error occurred during changing user password", e);

        }
    }

    private static final String GET_ALL_USERS = "SELECT u.id, u.username, u.email, r.name AS role_name FROM users u INNER JOIN roles r ON u.Roles_id = r.id";

    @Override
    public Map<String, User> getAllUsers() throws DaoException {

        LOGGER.log(Level.INFO, "Method getAllUsers is called");

        Map<String, User> usersMap = new HashMap<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {

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
        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.SEVERE, "Error retrieving users from the database", e);
            throw new DaoException("Error retrieving users from the database", e);

        }


        LOGGER.log(Level.INFO, "Method getAllUsers is finished");
        return usersMap;
    }
}
