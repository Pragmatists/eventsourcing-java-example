package com.pragmatists.eventsourcing.api;


public interface EventStream extends Iterable<Event> {
    long version();
}
