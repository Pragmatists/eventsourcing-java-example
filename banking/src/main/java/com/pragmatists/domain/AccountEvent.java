package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

public abstract class AccountEvent implements Event{

    private AccountId accountId;

    public AccountEvent(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId id() {
        return accountId;
    }

    @Override
    public AggregateId getAggregateId() {
        return id();
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public String getEventType() {
        return this.getClass().getSimpleName();
    }

    public abstract void applyOn(Account a);
}
