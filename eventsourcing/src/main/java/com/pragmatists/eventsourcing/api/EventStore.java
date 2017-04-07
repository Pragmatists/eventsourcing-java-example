package com.pragmatists.eventsourcing.api;


import java.util.List;
import java.util.stream.Stream;

public interface EventStore<Event> {
    Stream<Event> loadEventStream(AggregateId aggregateId);
    void store(AggregateId aggregateId, List<Event> events);
}