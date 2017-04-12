package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class InMemoryEventStream implements EventStream {

    private final long version;
    private final List<Event> stream;

    public InMemoryEventStream(List<Event> stream) {
        this.stream = stream;
        this.version = 0;
    }

    public long version() {
        return 0;
    }

    public Iterator<Event> iterator() {
        return stream.iterator();
    }

    public static InMemoryEventStream empty() {
        return new InMemoryEventStream(new ArrayList<>());
    }

    @Override
    public void addAll(List<Event> changes) {
        stream.addAll(changes);
    }
}
