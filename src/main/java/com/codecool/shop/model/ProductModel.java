package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class ProductModel extends BaseModel {

    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private ProductSubcategoryModel productSubcategory;
    private SupplierModel supplier;


    public ProductModel(String name, BigDecimal defaultPrice, String currencyString, String description, ProductSubcategoryModel productSubcategory, SupplierModel supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductSubcategory(productSubcategory);
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() { return this.defaultCurrency.toString() + this.defaultPrice; }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductSubcategoryModel getProductSubcategory() {
        return productSubcategory;
    }

    public void setProductSubcategory(ProductSubcategoryModel productSubcategory) {
        this.productSubcategory = productSubcategory;
        this.productSubcategory.addProduct(this);
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %1$d,\"name\": \"%2$s\",\"defaultPrice\": %3$f,\"price\":\"%4$s\",\"subcategory\":\"%5$s\",\"supplier\":\"%6$s\",\"description\":\"%7$s\"}",
                this.id, this.name, this.defaultPrice, this.getPrice(), this.productSubcategory.getName(), this.supplier.getName(), this.description);

//        return String.format("id: %1$d, " +
//                        "name: %2$s, " +
//                        "defaultPrice: %3$f, " +
//                        "defaultCurrency: %4$s, " +
//                        "productCategory: %5$s, " +
//                        "supplier: %6$s",
//                this.id,
//                this.name,
//                this.defaultPrice,
//                this.defaultCurrency.toString(),
//                this.productSubcategory.getName(),
//                this.supplier.getName());
    }
}
