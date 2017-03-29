package com.pragmatists.eventsourcing.api.store;


import com.pragmatists.eventsourcing.api.Event;

public interface EventStream<V> extends Iterable<Event> {
    V version();
}
