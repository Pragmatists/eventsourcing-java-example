package com.pragmatists.eventsourcing.api;


import java.util.List;

public interface EventStore<A, I extends AggregateId> {
    EventStream<A, I> loadEventStream(I aggregateId);
    void store(I aggregateId, long version, List<Event<A, I>> events);

}