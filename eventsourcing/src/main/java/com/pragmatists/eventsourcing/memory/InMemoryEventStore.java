package com.pragmatists.eventsourcing.memory;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryEventStore<A, I extends AggregateId> implements EventStore<A, I> {

    private final List<Event<A, I>> events = new ArrayList<>();

    public EventStream<A, I> loadEventStream(final I aggregateId) {
        return new InMemoryEventStream(events.stream().filter(event -> event.getAggregateId().equals(aggregateId)).collect(Collectors.toList()));
    }

    public void store(I aggregateId, long version, List<Event<A, I>> events) {
        this.events.addAll(events);
    }


}
