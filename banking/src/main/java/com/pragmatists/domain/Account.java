package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private List<Event> changes = new ArrayList<>();
    private final AccountId id;
    private int version = 0;
    private String owner;

    protected Account(AccountId accountId) {
        this.id = accountId;
    }

    public static Account create(String id, String owner) {
        final Account account = new Account(new AccountId(id));
        account.createInternal(owner);
        return account;
    }

    private void createInternal(String owner) {
        final AccountCreated accountCreated = new AccountCreated(this.id, owner);
        apply(accountCreated);
        changes.add(accountCreated);
    }

    public AccountId getId() {
        return id;
    }

    public String getNumber() {
        return id.getValue();
    }

    List<Event> getChanges() {
        return new ArrayList<>(changes);
    }

    public int getVersion() {
        return this.version;
    }

    public void applyEvents(EventStream events) {
        for (Event event : events) {
            apply(event);
        }
    }

    private void apply(Event event) {
        if (event.getEventType().equals("AccountCreated")) {
            apply((AccountCreated) event);
        }
    }

    private void apply(AccountCreated event) {
        this.owner = event.getOwner();
    }

    public String getOwner() {
        return owner;
    }

    public Integer getBalance() {
        return 0;
    }
}
