package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Event;

public class AccountClosed implements Event<Account, AccountId> {

    private final AccountId id;

    public AccountClosed(AccountId id) {
        this.id = id;
    }

    @Override
    public AccountId getAggregateId() {
        return id;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public String getEventType() {
        return "ACCOUNT_CLOSED";
    }

    @Override
    public void applyOn(Account aggregate) {
        aggregate.apply(this);

    }
}
