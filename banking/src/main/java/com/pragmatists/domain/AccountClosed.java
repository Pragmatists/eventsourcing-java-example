package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

public class AccountClosed implements Event<Account> {
    private final AggregateId aggregateId;

    public AccountClosed(AggregateId aggregateId) {
        this.aggregateId = aggregateId;
    }

    @Override
    public AggregateId getAggregateId() {
        return aggregateId;
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
        account.apply(this);
    }

}
