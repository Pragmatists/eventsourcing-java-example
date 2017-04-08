package com.pragmatists.eventsourcing.api;


public interface EventStream<A, I extends AggregateId> extends Iterable<Event<A, I>> {
    long version();
}
