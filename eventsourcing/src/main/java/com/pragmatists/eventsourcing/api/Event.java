package com.pragmatists.eventsourcing.api;


public interface Event<A, I extends AggregateId> {
    I getAggregateId();

    int getVersion();

    String getEventType();

    void applyOn(A aggregate);
}
