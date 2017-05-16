package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

import java.time.LocalDate;

class AccountClosed implements Event<Account>{
    private AccountId id;
    private LocalDate date;

    public AccountClosed(AccountId id, LocalDate date) {
        this.id = id;
        this.date = date;
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

    @Override
    public LocalDate getDate() {
        return date;
    }
}
