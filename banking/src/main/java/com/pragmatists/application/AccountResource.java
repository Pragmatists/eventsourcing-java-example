package com.pragmatists.application;


public class AccountResource {

    private String accountId;
    private String number;
    private String owner;
    private String balance;

    public AccountResource() {
    }

    public AccountResource(String accountId, String number, String owner, String balance) {
        this.accountId = accountId;
        this.number = number;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public String getBalance() {
        return balance;
    }
}
