package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

import java.time.LocalDate;


class AccountCreated implements Event<Account> {

    private final AggregateId id;
    private final String owner;
    private final LocalDate date;
    private String number;

    AccountCreated(AccountId id, String owner, String number, LocalDate date) {
        this.id = id;
        this.owner = owner;
        this.number = number;
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

    public String getOwner() {
        return owner;
    }


    public String getNumber() {
        return number;
    }
}
