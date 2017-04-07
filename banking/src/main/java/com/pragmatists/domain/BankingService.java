package com.pragmatists.domain;

public class BankingService {

    private AccountRepository accountRepository;

    public BankingService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    public void createAccount(String id, String owner) {
        final Account account = Account.create(id, owner);
        accountRepository.store(account);
    }

    public Account loadAccount(String accountId) {
        return accountRepository.load(new AccountId(accountId));
    }
}
