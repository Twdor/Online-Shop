package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductSubcategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductModel;
import com.codecool.shop.model.ProductSubcategoryModel;
import com.codecool.shop.model.SupplierModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc extends DbManager implements ProductDao {
    private static DataSource dataSource;
    private static ProductDaoJdbc instance = null;
    private static ProductSubcategoryDao productSubcategoryDao;
    private static SupplierDao supplierDao;

    private ProductDaoJdbc() {};

    public static ProductDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            dataSource = connect();
            productSubcategoryDao = ProductSubcategoryDaoJdbc.getInstance();
            supplierDao = SupplierDaoJdbc.getInstance();
            instance = new ProductDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(ProductModel product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO products (" +
                    "name, " +
                    "price, " +
                    "currency, " +
                    "description, " +
                    "product_subcategory_id, " +
                    "product_supplier_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getDefaultPrice());
            statement.setString(3, product.getDefaultCurrency().toString());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getProductSubcategory().getId());
            statement.setInt(6, product.getSupplier().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductModel product = new ProductModel(
                    rs.getString(2),
                    rs.getBigDecimal(3),
                    rs.getString(4),
                    rs.getString(5),
                    productSubcategoryDao.find(rs.getInt(6)),
                    supplierDao.find(rs.getInt(7))
                    );
            product.setId(id);

            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading product with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductModel> getAll() {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM products";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductModel> products = new ArrayList<>();
            while (rs.next()) {
                ProductModel product = new ProductModel(
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getString(5),
                        productSubcategoryDao.find(rs.getInt(6)),
                        supplierDao.find(rs.getInt(7))
                );
                product.setId(rs.getInt(1));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<ProductModel> getBy(SupplierModel supplier) {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM products WHERE product_supplier_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = st.executeQuery();
            List<ProductModel> products = new ArrayList<>();
            while (rs.next()) {
                ProductModel product = new ProductModel(
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getString(5),
                        productSubcategoryDao.find(rs.getInt(6)),
                        supplier
                );
                product.setId(rs.getInt(1));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products with product_supplier_id: "+supplier.getId(), e);
        }
    }

    @Override
    public List<ProductModel> getBy(ProductSubcategoryModel productSubcategory) {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM products WHERE product_subcategory_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productSubcategory.getId());
            ResultSet rs = st.executeQuery();
            List<ProductModel> products = new ArrayList<>();
            while (rs.next()) {
                ProductModel product = new ProductModel(
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getString(5),
                        productSubcategory,
                        supplierDao.find(rs.getInt(7))
                );
                product.setId(rs.getInt(1));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products with product_subcategory_id: "+productSubcategory.getId(), e);
        }
    }
}
