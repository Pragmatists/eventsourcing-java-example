package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.EventStream;

public class AccountRepository {

    private final EventStore eventStore;

    public AccountRepository(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void store(Account account) {
        eventStore.store(account.getId(), account.getVersion(), account.getChanges());
    }

    public Account load(AccountId accountId) {
        final EventStream<Account> events = eventStore.loadEventStream(accountId);
        final Account account = new Account(accountId);
        events.forEach(event -> event.applyOn(account));
        return account;
    }
}
