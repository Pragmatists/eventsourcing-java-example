package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

import java.time.LocalDate;


public class AccountWithdrawn implements Event<Account> {
    private final AccountId id;
    private Integer amount;
    private final LocalDate date;

    public AccountWithdrawn(AccountId id, Integer amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
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

    public LocalDate getDate() {
        return date;
    }

    @Override
    public void applyOn(Account account) {
        account.apply(this);
    }
}
