package com.kostiaxn.gracefinance.model;

abstract public class Transaction {
    private int id;
    private String accountName;
    private String cardName;
    private double amount;
    private String place;
    private String comment;
    private String currency;
    private double exchangeRate;

    public Transaction(int id, String accountName, String cardName, double amount, String place, String comment, String currency, double exchangeRate) {
        this.id = id;
        this.accountName = accountName;
        this.cardName = cardName;
        this.amount = amount;
        this.place = place;
        this.comment = comment;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
