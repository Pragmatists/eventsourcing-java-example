package com.pragmatists.eventsourcing.api;


import com.pragmatists.eventsourcing.api.store.EventStream;

import java.util.List;
import java.util.UUID;

public interface EventStore<V> {
    EventStream<Long> loadEventStream(UUID aggregateId);
    void store(UUID aggregateId, long version, List<Event> events);

}