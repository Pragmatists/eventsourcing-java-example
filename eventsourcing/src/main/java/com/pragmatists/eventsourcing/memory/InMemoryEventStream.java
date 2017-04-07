package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class InMemoryEventStream implements EventStream {

    private final long version;
    private final List<Event> events;

    public static InMemoryEventStream empty() {
        return new InMemoryEventStream(new ArrayList<>());
    }

    private InMemoryEventStream(List<Event> events) {
        this.version = 0;
        this.events = events;
    }

    public Long version() {
        return version;
    }

    @Override
    public void addAll(List<Event> changes) {
        events.addAll(changes);
    }

    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
