package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private final List<Event<Account, AccountId>> newEvents = new ArrayList<>();

    private String owner;
    private AccountId id;
    private BigDecimal balance;
    private boolean closed;

    Account(EventStream<Account, AccountId> eventStream) {
        eventStream.forEach(event -> event.applyOn(this));
    }

    public Account(AccountId id, String owner) {
        this.id = id;
        this.owner = owner;
        this.balance = BigDecimal.ZERO;
        newEvents.add(new AccountCreated(id, owner));
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        newEvents.add(new MoneyDeposited(id, amount));
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
        newEvents.add(new MoneyWithdrawn(id, amount));
    }

    public void close() {
        closed = true;
        newEvents.add(new AccountClosed(id));
    }

    void apply(AccountCreated accountCreated) {
        id = accountCreated.getAggregateId();
        owner = accountCreated.getOwner();
        balance = BigDecimal.ZERO;
    }

    void apply(MoneyDeposited moneyDeposited) {
        balance = balance.add(moneyDeposited.amount());
    }

    void apply(MoneyWithdrawn moneyWithdrawn) {
        balance = balance.subtract(moneyWithdrawn.amount());
    }

    void apply(AccountClosed accountClosed) {
        closed = true;
    }

    List<Event<Account, AccountId>> newEvents() {
        return newEvents;
    }

    AccountId getId() {
        return id;
    }

    public String owner() {
        return owner;
    }

    public BigDecimal balance() {
        return balance;
    }

    public boolean isClosed() {
        return closed;
    }
}
