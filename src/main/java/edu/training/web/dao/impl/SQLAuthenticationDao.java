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

    public SQLAuthenticationDao() {
    }

    @Override
    public User signIn(AuthenticationInfo authenticationInfo) throws DaoException {

        String signInSql = "SELECT u.username, u.email, r.name FROM users u JOIN roles r ON u.Roles_id = r.id WHERE u.email = ? AND u.password = ?";

        try (PreparedStatement statement = connection.prepareStatement(signInSql)) {

            statement.setString(1, authenticationInfo.getLogin());
            statement.setString(2, authenticationInfo.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String username = resultSet.getString("username");
                    String roleName = resultSet.getString("name");
                    UserRoles role = UserRoles.valueOf(roleName.toUpperCase());

                    return new User(username, role);

                } else {
                    throw new DaoException("Invalid login or password");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error occurred during sign in", e);
        }
    }

    @Override
    public boolean userExists(String email) throws DaoException {

        String checkUserSql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(checkUserSql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }

                return false;

            }
        } catch (SQLException e) {
            throw new DaoException("Database error occurred while checking user existence", e);
        }
    }


    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws DaoException {

        String insertUserSql = "INSERT INTO users (username, email, password, Roles_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertUserSql)) {

            statement.setString(1, userRegistrationInfo.getLogin());
            statement.setString(2, userRegistrationInfo.getEmail());
            statement.setString(3, userRegistrationInfo.getPassword());
            statement.setInt(4, 4);

            return new User(userRegistrationInfo.getLogin(), UserRoles.READER);

        } catch (SQLException e) {

            throw new DaoException("Database error occurred during sign up", e);

        }
    }

    @Override
    public Map<String, User> getAllUsers() throws DaoException {

        Map<String, User> usersMap = new HashMap<>();

        String sql = "SELECT u.id, u.username, u.email, r.name AS role_name FROM users u INNER JOIN roles r ON u.Roles_id = r.id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String roleName = resultSet.getString("role_name");
                    UserRoles role = UserRoles.valueOf(roleName.toUpperCase());

                    User user = new User(username, role);

                    usersMap.put(email, user);
                }
            }
        } catch (SQLException e) {

            throw new DaoException("Error retrieving users from the database", e);

        }
        return usersMap;
    }
}
