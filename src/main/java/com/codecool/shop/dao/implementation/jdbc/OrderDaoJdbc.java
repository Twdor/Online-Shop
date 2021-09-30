package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.models.OrderModel;
import com.codecool.shop.models.ProductModel;
import com.codecool.shop.models.ShoppingCartModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbc extends DbManager implements OrderDao {
    private static DataSource dataSource;
    private static OrderDaoJdbc instance = null;


    private OrderDaoJdbc() {};

    public static OrderDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            dataSource = connect();
            instance = new OrderDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(OrderModel orderModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO orders (id, customer_id, customer_type, order_date, order_status, total_price, product_list) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, orderModel.getOrderId());
            statement.setString(2, orderModel.getCustomerId());
            statement.setString(3, orderModel.getCustomerType());
            statement.setString(4, orderModel.getOrderDate());
            statement.setString(5, orderModel.getOrderStatus());
            statement.setString(6, orderModel.getTotalPrice());
            statement.setString(7, orderModel.getProductList().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderModel> getAll(String customerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM orders WHERE customer_id LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, customerId);

            ResultSet rs = st.executeQuery();
            List<OrderModel> orders = new ArrayList<>();
            while (rs.next()) {
                OrderModel order = new OrderModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                var productList = new ArrayList<>();
                productList.add(rs.getString(7));
                order.setProductList(productList);

                orders.add(order);

            }

            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading orders with customerId: " + customerId, e);
        }
    }
}
