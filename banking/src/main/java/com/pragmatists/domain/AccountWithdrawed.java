package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

public class AccountWithdrawed implements Event<Account> {
    private final AccountId accountId;
    private final Integer amount;

    public AccountWithdrawed(AccountId accountId, Integer amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    @Override
    public AggregateId getAggregateId() {
        return accountId;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public String getEventType() {
        return getClass().getSimpleName();
    }

    @Override
    public void applyOn(Account account) {
        account.setBalance(account.getBalance() - amount);
    }
}
