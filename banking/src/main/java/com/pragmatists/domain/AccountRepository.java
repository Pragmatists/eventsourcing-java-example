package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.EventStream;

public class AccountRepository {

    private final EventStore<Account, AccountId> eventStore;

    public AccountRepository(EventStore<Account, AccountId> eventStore) {
        this.eventStore = eventStore;
    }

    public void store(Account account) {
        eventStore.store(account.getId(), 1, account.newEvents());
    }

    public Account load(AccountId accountId) {
        EventStream<Account, AccountId> eventStream = eventStore.loadEventStream(accountId);
        return new Account(eventStream);
    }
}
