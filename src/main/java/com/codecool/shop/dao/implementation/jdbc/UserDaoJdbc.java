package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.models.UserModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class UserDaoJdbc extends DbManager implements UserDao {
    private static DataSource dataSource;
    private static UserDaoJdbc instance = null;

    private UserDaoJdbc() {};

    public static UserDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            dataSource = connect();
            instance = new UserDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(UserModel userModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userModel.getName());
            statement.setString(2, userModel.getEmail());
            statement.setString(3, userModel.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            userModel.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(UserModel userModel) {

    }

    @Override
    public void update(UserModel userModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE users SET name = ?, email = ?, phone_number = ?, country = ?, state = ?, city = ?, zipcode = ?, address = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userModel.getName());
            st.setString(2, userModel.getEmail());
            st.setString(3, userModel.getPhoneNumber());
            st.setString(4, userModel.getCountry());
            st.setString(5, userModel.getState());
            st.setString(6, userModel.getCity());
            st.setString(7, userModel.getZipcode());
            st.setString(8, userModel.getAddress());
            st.setInt(9, userModel.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            UserModel userModel = new UserModel(rs.getString(2), rs.getString(3), rs.getString(4));
            userModel.setId(rs.getInt(1));
            userModel.setPhoneNumber(rs.getString(5));
            userModel.setCountry(rs.getString(6));
            userModel.setCity(rs.getString(7));
            userModel.setZipcode(rs.getString(8));
            userModel.setAddress(rs.getString(9));

            return userModel;

        } catch (SQLException e) {
            throw new RuntimeException("Error while reading customer with id: " + id, e);
        }
    }

    @Override
    public UserModel find(String email){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM users WHERE email LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            UserModel userModel = new UserModel(rs.getString(2), rs.getString(3), rs.getString(4));
            userModel.setId(rs.getInt(1));
            userModel.setPhoneNumber(rs.getString(5));
            userModel.setCountry(rs.getString(6));
            userModel.setCity(rs.getString(7));
            userModel.setZipcode(rs.getString(8));
            userModel.setAddress(rs.getString(9));

            return userModel;

        } catch (SQLException e) {
            throw new RuntimeException("Error while reading customer with email: " + email, e);
        }
    }

    @Override
    public List<UserModel> getAll() {
        return null;
    }

}
