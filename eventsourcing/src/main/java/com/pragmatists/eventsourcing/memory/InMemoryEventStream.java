package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class InMemoryEventStream<T> implements EventStream<T> {

    private final long version;
    private final List<Event<T>> events;

    public static <T> InMemoryEventStream<T> empty() {
        return new InMemoryEventStream<T>(new ArrayList<>());
    }

    private InMemoryEventStream(List<Event<T>> events) {
        this.version = 0;
        this.events = events;
    }

    public Long version() {
        return version;
    }

    @Override
    public void addAll(List<Event<T>> changes) {
        events.addAll(changes);
    }

    public Iterator<Event<T>> iterator() {
        return events.iterator();
    }
}
