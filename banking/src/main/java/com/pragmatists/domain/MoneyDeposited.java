package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Event;

import java.math.BigDecimal;

public class MoneyDeposited implements Event<Account, AccountId> {

    private final AccountId id;
    private final BigDecimal amount;

    public MoneyDeposited(AccountId id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
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
        return "MONEY_DEPOSITED";
    }

    @Override
    public void applyOn(Account aggregate) {
        aggregate.apply(this);
    }

    BigDecimal amount() {
        return amount;
    }
}
