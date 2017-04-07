package com.pragmatists.domain;

public class BankingService {

    private AccountRepository accountRepository;

    public BankingService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

}
