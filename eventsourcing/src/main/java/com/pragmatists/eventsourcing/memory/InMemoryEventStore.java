package com.pragmatists.eventsourcing.memory;


import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.store.EventStream;

import java.util.List;
import java.util.UUID;

public class InMemoryEventStore<V> implements EventStore<V> {

    public EventStream<Long> loadEventStream(UUID aggregateId) {
        return null;
    }

    public void store(UUID aggregateId, long version, List<Event> events) {

    }


}
