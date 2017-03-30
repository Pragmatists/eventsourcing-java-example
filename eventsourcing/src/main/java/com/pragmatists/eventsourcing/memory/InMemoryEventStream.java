package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class InMemoryEventStream<V> implements EventStream<V> {

    private final long version;
    private final List<Event> events;

    public InMemoryEventStream() {
        this.version = 0;
        events = Collections.emptyList();
    }

    public V version() {
        return null;
    }

    public Iterator<Event> iterator() {
        return null;
    }
}
