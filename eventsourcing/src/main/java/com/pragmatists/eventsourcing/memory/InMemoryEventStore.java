package com.pragmatists.eventsourcing.memory;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventStore implements EventStore {

    private List<Event> events = new ArrayList<>();

    public EventStream loadEventStream(final AggregateId aggregateId) {
        return new InMemoryEventStream(events.stream().filter(e -> e.getAggregateId().equals(aggregateId)));
    }

    public void store(AggregateId aggregateId, long version, List<Event> events) {
        this.events.addAll(events);
    }


}
