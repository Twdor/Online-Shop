package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.SupplierModel;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DbManager implements SupplierDao {
    private static DataSource dataSource;
    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {};

    public static SupplierDaoJdbc getInstance() throws SQLException {
        if (instance == null) {
            dataSource = connect();
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(SupplierModel supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO suppliers (name, description) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SupplierModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM suppliers WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            SupplierModel supplier = new SupplierModel(rs.getString(1), rs.getString(2));
            supplier.setId(id);

            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading supplier with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<SupplierModel> getAll() {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT * FROM suppliers";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<SupplierModel> suppliers = new ArrayList<>();
            while (rs.next()) {
                SupplierModel supplier = new SupplierModel(rs.getString(2), rs.getString(3));
                supplier.setId(rs.getInt(1));
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }
}
