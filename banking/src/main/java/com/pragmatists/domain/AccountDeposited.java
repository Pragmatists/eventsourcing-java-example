package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;

public class AccountDeposited implements Event<Account> {
    private final int amount;

    public AccountDeposited(int amount) {
        this.amount = amount;
    }

    @Override
    public AggregateId getAggregateId() {
        return null;
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
        account.setBalance(account.getBalance() + amount);

    }
}
