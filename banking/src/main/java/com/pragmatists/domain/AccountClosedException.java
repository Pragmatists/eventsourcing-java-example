package com.pragmatists.domain;

public class AccountClosedException extends RuntimeException {
    private final AccountId accountId;

    public AccountClosedException(AccountId accountId) {
        this.accountId = accountId;
    }
}
