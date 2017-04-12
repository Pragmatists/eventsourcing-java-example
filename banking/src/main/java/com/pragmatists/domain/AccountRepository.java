package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.EventStream;


public class AccountRepository {

    private final EventStore eventStore;

    public AccountRepository(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    void store(Account account) {
        eventStore.store(account.id(), 0, account.getChanges());
    }

    Account load(AccountId accountId) {
        final EventStream events = eventStore.loadEventStream(accountId);
        final Account account = new Account(accountId);
        events.forEach(event -> event.applyOn(account));
        return account;
    }
}
