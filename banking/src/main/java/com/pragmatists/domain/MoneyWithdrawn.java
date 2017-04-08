package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Event;

import java.math.BigDecimal;

public class MoneyWithdrawn implements Event<Account, AccountId> {

    private final AccountId id;
    private final BigDecimal amount;

    public MoneyWithdrawn(AccountId id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
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
        return "MONEY_WITHDRAWN";
    }

    @Override
    public void applyOn(Account aggregate) {
        aggregate.apply(this);
    }

    public BigDecimal amount() {
        return amount;
    }
}
