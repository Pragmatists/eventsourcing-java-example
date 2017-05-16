package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

import java.time.LocalDate;


public class AccountDeposited implements Event<Account> {
    private final AccountId id;
    private final Integer amount;
    private final LocalDate date;

    public AccountDeposited(AccountId id, Integer amount, LocalDate date) {

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

    public LocalDate getDate(){
        return date;
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
