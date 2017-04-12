package com.pragmatists.domain;

public class BankingService {

    private AccountRepository accounts;

    public BankingService(AccountRepository accounts) {

        this.accounts = accounts;
    }

}
