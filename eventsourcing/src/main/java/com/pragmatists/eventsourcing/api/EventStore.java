package com.pragmatists.eventsourcing.api;


import java.util.List;

public interface EventStore<V> {
    EventStream<Long> loadEventStream(AggregateId aggregateId);
    void store(AggregateId aggregateId, long version, List<Event> events);

}