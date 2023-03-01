package com.kostiaxn.gracefinance.model;

public class Expense extends Transaction {
    private String productCategory;
    private String productSubCategory;
    private String productName;

    public Expense(int id, String accountName, String cardName, double amount, String place,
                   String comment, String currency, double exchangeRate, String productCategory,
                   String productSubCategory, String productName) {
        super(id, accountName, cardName, amount, place, comment, currency, exchangeRate);
        this.productCategory = productCategory;
        this.productSubCategory = productSubCategory;
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}

