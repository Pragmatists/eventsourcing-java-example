package com.pragmatists.eventsourcing.memory;


import com.pragmatists.eventsourcing.api.AggregateId;
import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class InMemoryEventStore implements EventStore<Event> {

    private Map<AggregateId, List<Event>> idToEvents = new HashMap<AggregateId, List<Event>>();


    public Stream<Event> loadEventStream(AggregateId aggregateId) {
        return idToEvents.get(aggregateId).stream();
    }

    public void store(AggregateId aggregateId, List<Event> events) {
        if(!idToEvents.containsKey(aggregateId)) {
            idToEvents.put(aggregateId, new ArrayList<Event>());
        }
        idToEvents.get(aggregateId).addAll(events);
    }
}
