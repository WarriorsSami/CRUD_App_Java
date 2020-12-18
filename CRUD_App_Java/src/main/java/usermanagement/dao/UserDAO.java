package usermanagement.dao;

import usermanagement.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// This DAO (Data Access Object) class provides CRUD database operations for table users from our DB
public class UserDAO {
    // Data Base Connection Variables
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "";

    // Generic SQL commands template
    private static final String INSERT_USER = "INSERT INTO users (name, email, country) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET name=?, email=?, country=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    // Create JDBC MySQL Connection
    protected Connection getConnection () {
        Connection connection = null;

        try {
            Class.forName ("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection (jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }

        return connection;
    }

    // Create/Insert User
    public void insertUser (User user) throws SQLException {
        try (Connection connection = getConnection ();
                PreparedStatement statement = connection.prepareStatement (INSERT_USER)) {
            statement.setString (1, user.getName ());
            statement.setString (2, user.getEmail ());
            statement.setString (3, user.getCountry ());
            statement.executeUpdate ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    // Update User
    public boolean updateUser (User user) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = getConnection ();
                PreparedStatement statement = connection.prepareStatement (UPDATE_USER)) {
            statement.setString (1, user.getName ());
            statement.setString (2, user.getEmail ());
            statement.setString (3, user.getCountry ());
            statement.setInt (4, user.getID ());

            rowUpdated = statement.executeUpdate () > 0;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return rowUpdated;
    }

    // Delete User
    public boolean deleteUser (int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection ();
                PreparedStatement statement = connection.prepareStatement (DELETE_USER)) {
            statement.setInt (1, id);
            rowDeleted = statement.executeUpdate () > 0;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return rowDeleted;
    }

    // Select All Users
    public List <User> selectAllUsers () throws SQLException {
        List <User> users = new ArrayList <> ();
        try (Connection connection = getConnection ();
             PreparedStatement statement = connection.prepareStatement (SELECT_ALL_USERS)) {
            System.out.println (statement);

            ResultSet row = statement.executeQuery ();
            while (row.next ()) {
                int id = row.getInt ("id");
                String name = row.getString ("name");
                String email = row.getString ("email");
                String country = row.getString ("country");
                users.add (new User (id, name, email, country));
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return users;
    }

    // Select User by ID
    public User selectUser (int id) throws SQLException {
        User user = null;
        try (Connection connection = getConnection ();
                PreparedStatement statement = connection.prepareStatement (SELECT_USER_BY_ID)) {
            statement.setInt (1, id);
            System.out.println (statement);

            ResultSet row = statement.executeQuery ();
            while (row.next ()) {
                String name = row.getString ("name");
                String email = row.getString ("email");
                String country = row.getString ("country");
                user = new User (id, name, email, country);
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return user;
    }
}
