package com.kostiaxn.gracefinance.model;

public class Income extends Transaction{
    private String incomeCategory;
    private String incomeSubcategory;

    public Income(int id, String accountName, String cardName, double amount, String place, String comment, String currency, double exchangeRate, String incomeCategory, String incomeSubcategory) {
        super(id, accountName, cardName, amount, place, comment, currency, exchangeRate);
        this.incomeCategory = incomeCategory;
        this.incomeSubcategory = incomeSubcategory;
    }

    public String getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(String incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public String getIncomeSubcategory() {
        return incomeSubcategory;
    }

    public void setIncomeSubcategory(String incomeSubcategory) {
        this.incomeSubcategory = incomeSubcategory;
    }
}
