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
        final Account account = accountRepository.load(new AccountId(accountId));
        if (account.isClosed()) {
            throw new AccountClosedException(new AccountId(accountId));
        }
        return account;
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

    public void closeAccount(String id) {
        final Account account = accountRepository.load(new AccountId(id));
        account.close();
        accountRepository.store(account);
    }
}
