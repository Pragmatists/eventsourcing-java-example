package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

public class AccountDeposited implements Event<Account> {
    private final int amount;
    private final AggregateId aggregateId;

    public AccountDeposited(AggregateId aggregateId, int amount) {
        this.aggregateId = aggregateId;
        this.amount = amount;
    }

    @Override
    public AggregateId getAggregateId() {
        return aggregateId;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    public int getAmount() {
        return amount;
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
