package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {

    private AccountId id;
    private String number;
    private String owner;
    private Integer balance = 0;
    private boolean closed;
    private List<Event> changes = new ArrayList<>();

    Account() {
    }

    Account(AccountId accountId) {
        this.id = accountId;
    }

    void apply(AccountCreated accountCreated) {
        this.owner = accountCreated.getOwner();
        this.number = accountCreated.getNumber();
        this.balance = 0;
    }

    void apply(AccountDeposited accountDeposited) {
        this.balance += accountDeposited.getAmount();
    }

    void apply(AccountWithdrawed accountWithdrawed) {
        this.balance -= accountWithdrawed.getAmount();
    }

    void apply(AccountClosed accountClosed) {
        this.closed = true;
    }

    public AccountId id() {
        return id;
    }

    public Account createAccount(String owner) {
        this.id = AccountId.generate();
        this.owner = owner;
        this.number = UUID.randomUUID().toString();
        this.balance = 0;

        changes.add(new AccountCreated(id, owner, number));
        return this;
    }

    public void deposit(Integer value) {
        this.balance += value;
        changes.add(new AccountDeposited(id, value));
    }

    public void withdraw(Integer value) {
        this.balance -= value;
        changes.add(new AccountWithdrawed(id, value));
    }

    public void close() {
        this.closed = true;
        changes.add(new AccountClosed(id));
    }

    public boolean isClosed() {
        return closed;
    }

    public String number() {
        return number;
    }

    public String owner() {
        return owner;
    }

    public Integer balance() {
        return balance;
    }

    public List<Event> getChanges() {
        return changes;
    }
}
