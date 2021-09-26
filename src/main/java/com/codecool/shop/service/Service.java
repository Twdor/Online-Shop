package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.*;
import com.codecool.shop.model.*;

import java.sql.SQLException;
import java.util.List;

public class Service {
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private ProductDao productDao;
    private ProductSubcategoryDao productSubcategoryDao;
    private UserDao userDao;
    private ShoppingCartDao shoppingCartDao;

    //return if db initializer already occurred
    private boolean isInitializer = false;


    public Service() {
        this.productCategoryDao = ProductCategoryDaoMem.getInstance();
        this.productSubcategoryDao = ProductSubcategoryDaoMem.getInstance();
        this.supplierDao = SupplierDaoMem.getInstance();
        this.productDao = ProductDaoMem.getInstance();
        this.userDao = UserDaoMem.getInstance();
        this.shoppingCartDao = ShoppingCartDaoMem.getInstance();
    }

    public Service(boolean Jdbc) throws SQLException {
        this.productCategoryDao = ProductCategoryDaoJdbc.getInstance();
        this.productSubcategoryDao = ProductSubcategoryDaoJdbc.getInstance();
        this.supplierDao = SupplierDaoJdbc.getInstance();
        this.productDao = ProductDaoJdbc.getInstance();
        this.userDao = UserDaoJdbc.getInstance();
        this.shoppingCartDao = ShoppingCartDaoJdbc.getInstance();
        this.isInitializer = getProductCategoryById(1) != null;
    }

    //return if db initializer already occurred
    public boolean isInitializer() { return isInitializer; }

    public void add(ProductCategoryModel productCategoryModel) { this.productCategoryDao.add(productCategoryModel); }

    public void add(ProductSubcategoryModel productSubcategoryModel) { this.productSubcategoryDao.add(productSubcategoryModel); }

    public void add(SupplierModel supplierModel) { this.supplierDao.add(supplierModel); }

    public  void add(ProductModel product) { this.productDao.add(product); }

    public  void add(UserModel customer) { this.userDao.add(customer); }

    public void add(ShoppingCartModel shoppingCartModel) { this.shoppingCartDao.add(shoppingCartModel); }

    public UserModel getUser(String email) { return userDao.find(email); }

    public UserModel getUser(Integer userId) { return userDao.find(userId); }

    public List<UserModel> getAllUsers() { return userDao.getAll(); }

    public ShoppingCartModel getShoppingCartBy(String customerId, int productId) { return shoppingCartDao.find(customerId, productId); }

    public List<ShoppingCartModel> getCustomerShoppingCart(String customerId) { return shoppingCartDao.getAll(customerId); }

    public void deleteShoppingCart(int id) { shoppingCartDao.remove(id); }

    public void updateShoppingCart(ShoppingCartModel shoppingCartModel) { shoppingCartDao.update(shoppingCartModel); }

    public ProductCategoryModel getProductCategoryById(int id) {
        return productCategoryDao.find(id);
    }

    public List<ProductCategoryModel> getAllCategories() { return productCategoryDao.getAll(); }

    public ProductSubcategoryModel getProductSubcategory(int subcategoryId){
        return productSubcategoryDao.find(subcategoryId);
    }

    public List<ProductModel> getAllProducts() { return productDao.getAll(); }

    public ProductModel getProduct(int productId) { return productDao.find(productId); }

    public List<ProductModel> getProductsForSubcategory(int subcategoryId){
        var subcategory = productSubcategoryDao.find(subcategoryId);
        return productDao.getBy(subcategory);
    }

    public List<ProductSubcategoryModel> getProductSubcategoryByCategoryId(ProductCategoryModel categoryId) {
        return productSubcategoryDao.getAllByProductCategory(categoryId);
    }
}
