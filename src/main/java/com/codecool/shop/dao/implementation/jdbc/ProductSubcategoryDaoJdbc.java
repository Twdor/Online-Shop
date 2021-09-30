package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductSubcategoryDao;
import com.codecool.shop.models.ProductCategoryModel;
import com.codecool.shop.models.ProductSubcategoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSubcategoryDaoJdbc extends DbManager implements ProductSubcategoryDao {
    private static DataSource dataSource;
    private static ProductSubcategoryDaoJdbc instance = null;
    private static ProductCategoryDao productCategoryDao;

    private ProductSubcategoryDaoJdbc() {};

    public static ProductSubcategoryDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            dataSource = connect();
            productCategoryDao = ProductCategoryDaoJdbc.getInstance();
            instance = new ProductSubcategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductSubcategoryModel subcategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_subcategories (name, product_category_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, subcategory.getName());
            statement.setInt(2, subcategory.getProductCategory().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            subcategory.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductSubcategoryModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, product_category_id FROM product_subcategories WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductSubcategoryModel productSubcategory = new ProductSubcategoryModel(
                                                                            rs.getString(1),
                                                                            productCategoryDao.find(rs.getInt(2)));
            productSubcategory.setId(id);

            return productSubcategory;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading productSubcategory with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductSubcategoryModel> getAll() {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM product_subcategories";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductSubcategoryModel> productSubcategories = new ArrayList<>();
            while (rs.next()) {
                ProductSubcategoryModel productSubcategory = new ProductSubcategoryModel(
                                                                    rs.getString(2),
                                                                    productCategoryDao.find(rs.getInt(3)));
                productSubcategory.setId(rs.getInt(1));
                productSubcategories.add(productSubcategory);
            }
            return productSubcategories;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all productSubcategories", e);
        }
    }

    @Override
    public List<ProductSubcategoryModel> getAllByProductCategory(ProductCategoryModel productCategory) {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM product_subcategories WHERE product_category_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            List<ProductSubcategoryModel> productSubcategories = new ArrayList<>();
            while (rs.next()) {
                ProductSubcategoryModel productSubcategory = new ProductSubcategoryModel(rs.getString(2), productCategory);
                productSubcategory.setId(rs.getInt(1));
                productSubcategory.addProducts(ProductDaoJdbc.getInstance().getBy(productSubcategory));
                productSubcategories.add(productSubcategory);
            }
            return productSubcategories;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all productSubcategories with product_category_id: "+ productCategory.getId(), e);
        }
    }
}
