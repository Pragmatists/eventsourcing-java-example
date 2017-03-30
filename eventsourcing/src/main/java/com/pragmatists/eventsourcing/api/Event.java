package com.pragmatists.eventsourcing.api;


public interface Event {
    AggregateId getAggregateId();

    int getVersion();

    String getEventType();

}
