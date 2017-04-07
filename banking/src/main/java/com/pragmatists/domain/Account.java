package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.Event;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private List<Event> changes = new ArrayList<>();
    private final AccountId id;
    private int version = 0;
    private String owner;
    private int balance;
    private boolean closed;

    protected Account(AccountId accountId) {
        this.id = accountId;
    }

    public static Account createAccount(String id, String owner) {
        final Account account = new Account(new AccountId(id));
        account.create(owner);
        return account;
    }

    public void withdraw(Integer amount) {
        applyAndAddToChanges(new AccountWithdrawed(id, amount));
    }

    public void deposit(int amount) {
        applyAndAddToChanges(new AccountDeposited(id, amount));
    }

    public void close() {
        applyAndAddToChanges(new AccountClosed(id));
    }

    List<Event> getChanges() {
        return new ArrayList<>(changes);
    }

    public String getOwner() {
        return owner;
    }

    public AccountId getId() {
        return id;
    }

    public String getNumber() {
        return id.getValue();
    }

    public int getBalance() {
        return balance;
    }

    void setOwner(String owner) {
        this.owner = owner;
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isClosed() {
        return closed;
    }

    void setBalance(int newBalance) {
        this.balance = newBalance;
    }

    void markAsClosed() {
        this.closed = true;
    }

    private void create(String owner) {
        applyAndAddToChanges(new AccountCreated(this.id, owner));
    }

    private void applyAndAddToChanges(Event<Account> event) {
        event.applyOn(this);
        this.changes.add(event);
    }
}
