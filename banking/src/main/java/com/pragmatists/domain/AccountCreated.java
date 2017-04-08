package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Event;

public class AccountCreated implements Event<Account, AccountId> {

    private final AccountId id;
    private final String owner;

    public AccountCreated(AccountId id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    @Override
    public AccountId getAggregateId() {
        return id;
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getEventType() {
        return "ACCOUNT_CREATED";
    }

    @Override
    public void applyOn(Account aggregate) {
        aggregate.apply(this);
    }

    public String getOwner() {
        return owner;
    }
}
