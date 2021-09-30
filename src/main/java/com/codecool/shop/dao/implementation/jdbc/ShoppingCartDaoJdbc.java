package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.models.ShoppingCartModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoJdbc extends DbManager implements ShoppingCartDao {
    private static DataSource dataSource;
    private static ShoppingCartDaoJdbc instance = null;
    private static ProductDao productDao;

    private ShoppingCartDaoJdbc() {};

    public static ShoppingCartDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            dataSource = connect();
            productDao = ProductDaoJdbc.getInstance();
            instance = new ShoppingCartDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ShoppingCartModel shoppingCartModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO shopping_cards (customer_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, shoppingCartModel.getCustomerId());
            statement.setInt(2, shoppingCartModel.getProductId());
            statement.setInt(3, shoppingCartModel.getQuantity());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            shoppingCartModel.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM shopping_cards WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ShoppingCartModel shoppingCartModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE shopping_cards SET quantity = ? WHERE customer_id LIKE ? and product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, shoppingCartModel.getQuantity());
            st.setString(2, shoppingCartModel.getCustomerId());
            st.setInt(3, shoppingCartModel.getProductId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCartModel find(String customerId, int productId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT quantity FROM shopping_cards WHERE customer_id LIKE ? and product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, customerId);
            st.setInt(2, productId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new ShoppingCartModel(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading shopping_cards with customer id: " + customerId, e);
        }
    }

    @Override
    public List<ShoppingCartModel> getAll(String customerId) {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM shopping_cards WHERE customer_id LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, customerId);
            ResultSet rs = st.executeQuery();
            List<ShoppingCartModel> shoppingCartModels = new ArrayList<>();
            while (rs.next()) {
                ShoppingCartModel shoppingCartModel = new ShoppingCartModel(
                        rs.getInt(3),
                        customerId,
                        rs.getInt(4));
                shoppingCartModel.setId(rs.getInt(1));
                shoppingCartModels.add(shoppingCartModel);
            }
            return shoppingCartModels;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all shopping_cards with customer id: "+ customerId, e);
        }
    }
}
