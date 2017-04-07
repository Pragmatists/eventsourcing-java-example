package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


public class InMemoryEventStream implements EventStream {

    private final long version;
    private Stream<Event> stream;

    public InMemoryEventStream(Stream<Event> stream) {
        this.stream = stream;
        this.version = 0;
    }

    public long version() {
        return 0;
    }

    public Iterator<Event> iterator() {
        return stream.iterator();
    }
}
