package com.pragmatists.eventsourcing.memory;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEventStore<T> implements EventStore<T> {

    private Map<AggregateId, EventStream<T>> events = new HashMap<>();

    public EventStream<T> loadEventStream(AggregateId aggregateId) {
        return events.get(aggregateId);
    }

    public void store(AggregateId aggregateId, long version, List<Event<T>> changes) {
        events.computeIfAbsent(aggregateId, id -> InMemoryEventStream.empty()).addAll(changes);
    }


}
