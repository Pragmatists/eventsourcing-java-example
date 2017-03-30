package com.pragmatists.eventsourcing.api;


public interface Command {
    AggregateId aggregateId();
}
