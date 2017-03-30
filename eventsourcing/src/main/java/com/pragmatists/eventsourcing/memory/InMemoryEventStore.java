package com.pragmatists.eventsourcing.memory;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.List;

public class InMemoryEventStore<V> implements EventStore<V> {

    public EventStream<Long> loadEventStream(AggregateId aggregateId) {
        return null;
    }

    public void store(AggregateId aggregateId, long version, List<Event> events) {

    }


}
