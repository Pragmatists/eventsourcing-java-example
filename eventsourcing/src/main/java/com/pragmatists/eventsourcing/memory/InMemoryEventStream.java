package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.store.EventStream;

import java.util.Iterator;


public class InMemoryEventStream<V> implements EventStream<V> {
    public V version() {
        return null;
    }

    public Iterator<Event> iterator() {
        return null;
    }
}
