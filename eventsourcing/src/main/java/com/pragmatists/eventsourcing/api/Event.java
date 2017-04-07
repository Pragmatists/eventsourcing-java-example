package com.pragmatists.eventsourcing.api;


public interface Event<T> {
    AggregateId getAggregateId();

    int getVersion();

    String getEventType();

    void applyOn(T account);
}
