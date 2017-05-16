package com.pragmatists.eventsourcing.api;


import java.time.LocalDate;

public interface Event<T> {
    AggregateId getAggregateId();

    int getVersion();

    String getEventType();

    void applyOn(T account);

    LocalDate getDate();
}
