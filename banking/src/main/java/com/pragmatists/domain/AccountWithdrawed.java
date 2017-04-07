package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

public class AccountWithdrawed implements Event<Account> {
    private final Integer amount;

    public AccountWithdrawed(Integer amount) {
        this.amount = amount;
    }

    @Override
    public AggregateId getAggregateId() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public String getEventType() {
        return null;
    }

    @Override
    public void applyOn(Account account) {
        account.setBalance(account.getBalance() - amount);
    }
}
