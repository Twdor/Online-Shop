package com.codecool.shop.service;

import com.codecool.shop.config.ApplicationProperties;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductSubcategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.ProductSubcategoryDaoJdbc;
import com.codecool.shop.dao.implementation.jdbc.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductSubcategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ProductSubcategory;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductSubcategoryDao productSubcategoryDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    //return if db initializer already occurred
    private boolean isInitializer = false;


    public ProductService() {
        this.productCategoryDao = ProductCategoryDaoMem.getInstance();
        this.productSubcategoryDao = ProductSubcategoryDaoMem.getInstance();
        this.supplierDao = SupplierDaoMem.getInstance();
        this.productDao = ProductDaoMem.getInstance();
    }

    public ProductService(boolean Jdbc) throws SQLException {
        DataSource dataSource = connect();

        this.productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        this.productSubcategoryDao = new ProductSubcategoryDaoJdbc(dataSource);
        this.supplierDao = new SupplierDaoJdbc(dataSource);
        this.productDao = new ProductDaoJdbc(dataSource);
        this.isInitializer = getProductCategoryById(1) != null;
    }

    //return if db initializer already occurred
    public boolean isInitializer() { return isInitializer; }

    public void add(ProductCategory productCategory) { this.productCategoryDao.add(productCategory); }

    public void add(ProductSubcategory productSubcategory) { this.productSubcategoryDao.add(productSubcategory); }

    public void add(Supplier supplier) { this.supplierDao.add(supplier); }

    public  void add(Product product) { this.productDao.add(product); }

    public ProductCategory getProductCategoryById(int id) {
        return productCategoryDao.find(id);
    }

    public List<ProductCategory> getAllCategories() { return productCategoryDao.getAll(); }

    public ProductSubcategory getProductSubcategory(int subcategoryId){
        return productSubcategoryDao.find(subcategoryId);
    }

    public List<Product> getAllProducts() { return productDao.getAll(); }

    public List<Product> getProductsForSubcategory(int subcategoryId){
        var subcategory = productSubcategoryDao.find(subcategoryId);
        return productDao.getBy(subcategory);
    }

    public List<ProductSubcategory> getProductSubcategoryByCategoryId(ProductCategory categoryId) {
        return productSubcategoryDao.getAllByProductCategory(categoryId);
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        ApplicationProperties properties = new ApplicationProperties();

        String dbName = properties.readProperty("DATABASE");
        String user = properties.readProperty("USERNAME");
        String password = properties.readProperty("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }


}
