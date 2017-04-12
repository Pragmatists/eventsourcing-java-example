package com.pragmatists.eventsourcing.api;


import java.util.List;

public interface EventStream extends Iterable<Event> {
    long version();

    void addAll(List<Event> changes);
}
