package com.pragmatists.domain;

public class BankingService {

    private AccountRepository accountRepository;

    public BankingService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    public void createAccount(String id, String owner) {
        final Account account = Account.createAccount(id, owner);
        accountRepository.store(account);
    }

    public Account loadAccount(String accountId) {
        return accountRepository.load(new AccountId(accountId));
    }

    public void deposit(String id, String amount) {
        final Account account = accountRepository.load(new AccountId(id));
        account.deposit(Integer.valueOf(amount));
        accountRepository.store(account);
    }

    public void withdraw(String id, String amount) {
        final Account account = accountRepository.load(new AccountId(id));
        account.withdraw(Integer.valueOf(amount));
        accountRepository.store(account);
    }
}
