package com.pragmatists.eventsourcing.api;


import java.util.List;
import java.util.function.Predicate;

public interface EventStore {
    EventStream loadEventStream(AggregateId aggregateId);
    EventStream loadEventStream(Predicate<Event> predicate);
    void store(AggregateId aggregateId, long version, List<Event> events);

}