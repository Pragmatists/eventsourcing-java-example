package com.pragmatists.eventsourcing.api;


import java.util.List;

public interface EventStream<T> extends Iterable<Event<T>> {
    Long version();

    void addAll(List<Event<T>> changes);
}
