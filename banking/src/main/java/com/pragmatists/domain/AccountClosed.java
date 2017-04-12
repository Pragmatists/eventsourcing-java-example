package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

class AccountClosed implements Event<Account>{
    private AccountId id;

    public AccountClosed(AccountId id) {

        this.id = id;
    }

    @Override
    public AggregateId getAggregateId() {
        return id;
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
        account.apply(this);
    }
}
