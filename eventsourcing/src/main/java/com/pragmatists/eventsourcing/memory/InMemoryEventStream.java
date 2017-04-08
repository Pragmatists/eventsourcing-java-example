package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.Iterator;
import java.util.List;


public class InMemoryEventStream implements EventStream {

    private final List<Event> events;

    public InMemoryEventStream(List<Event> events) {
        this.events = events;
    }

    public long version() {
        return 1;
    }

    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
