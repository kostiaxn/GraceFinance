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
}
