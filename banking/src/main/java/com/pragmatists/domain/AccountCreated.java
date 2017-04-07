package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

public class AccountCreated implements Event {
    private final AggregateId id;
    private final String owner;

    public AccountCreated(AccountId id, String owner) {
        this.id = id;
        this.owner = owner;
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
        return this.getClass().getSimpleName();
    }

    public String getOwner() {
        return owner;
    }
}
