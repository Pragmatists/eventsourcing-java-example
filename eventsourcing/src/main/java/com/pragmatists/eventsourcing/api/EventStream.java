package com.pragmatists.eventsourcing.api;


public interface EventStream<V> extends Iterable<Event> {
    V version();
}
