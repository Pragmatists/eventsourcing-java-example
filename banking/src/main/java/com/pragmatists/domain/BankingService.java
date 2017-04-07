package com.pragmatists.domain;

public class BankingService {

    private AccountRepository accountRepository;

    public BankingService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    public AccountId createAccount(String owner) {
        AccountId accountId = AccountId.generate();
        accountRepository.create(accountId, owner);
        return accountId;
    }

    public Account getAccountBy(AccountId accountId) {
        return accountRepository.load(accountId);
    }

    public void deposit(AccountId accountId, int ammount) {
        accountRepository.deposit(accountId, ammount);
    }
}

