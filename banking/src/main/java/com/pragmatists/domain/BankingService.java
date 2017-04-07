package com.pragmatists.domain;

import java.util.Optional;

public class BankingService {

    private AccountRepository accounts;

    public BankingService(AccountRepository accountRepository) {

        this.accounts = accountRepository;
    }

    public AccountId newAccount(String owner) {
        Account account = new Account(AccountId.generate(), owner);
        accounts.store(account);
        return account.id();
    }

    public void closeAccount(AccountId accountId) {
        accounts.remove(accountId);
    }

    public Optional<Account> load(AccountId id) {
        return accounts.load(id);
    }

    public void deposit(AccountId id, Integer amount) {

        System.err.println("deposit: " + amount);

        accounts.load(id).ifPresent(a -> a.deposit(amount));
    }

    public void withdraw(AccountId id, AccountId from, Integer amount) {
        accounts.load(id).ifPresent(a -> a.withdraw(amount));
    }
}
