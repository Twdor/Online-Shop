package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc extends DbManager implements ProductCategoryDao {
    private static DataSource dataSource;
    private static ProductCategoryDaoJdbc instance = null;


    private ProductCategoryDaoJdbc() {};

    public static ProductCategoryDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            dataSource = connect();
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategoryModel category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_categories (name, department) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDepartment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductCategoryModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, department FROM product_categories WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategoryModel productCategory = new ProductCategoryModel(rs.getString(1), rs.getString(2));
            productCategory.setId(id);

            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading category with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategoryModel> getAll() {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM product_categories";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductCategoryModel> productCategories = new ArrayList<>();
            while (rs.next()) {
                ProductCategoryModel productCategory = new ProductCategoryModel(rs.getString(2), rs.getString(3));
                productCategory.setId(rs.getInt(1));
                productCategories.add(productCategory);
            }
            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all productCategories", e);
        }
    }
}
