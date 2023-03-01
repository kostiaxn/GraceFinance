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
}

