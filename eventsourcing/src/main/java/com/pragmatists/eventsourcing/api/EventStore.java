package com.pragmatists.eventsourcing.api;


import java.util.List;

public interface EventStore<T> {
    EventStream<T> loadEventStream(AggregateId aggregateId);
    void store(AggregateId aggregateId, long expectedVersion, List<Event<T>> events);

}