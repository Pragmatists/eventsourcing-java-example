package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.memory.InMemoryEventStore;

public class AccountRepository {

    public AccountRepository(EventStore<Object> eventStore) {

    }
}
