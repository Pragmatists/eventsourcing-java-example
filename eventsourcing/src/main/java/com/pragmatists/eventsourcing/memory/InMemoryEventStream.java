package com.pragmatists.eventsourcing.memory;

import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;
import com.pragmatists.eventsourcing.api.GroupedEventStream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class InMemoryEventStream implements EventStream {

    private final long version;
    private Stream<Event> stream;

    public InMemoryEventStream(Stream<Event> events) {
        this.stream = events;
        this.version = 0;
    }

    public InMemoryEventStream(List<Event> events) {
        this(events.stream());
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
        stream = Stream.concat(stream, changes.stream());
    }

    @Override
    public <P> P project(Projection<P> projection) {
        P acc = projection.init();
        stream.forEach(el -> projection.onEvent(acc, el));
        return acc;
    }



    @Override
    public <G> GroupedEventStream<G> groupBy(Function<Event, G> grouper) {
        return new GroupedEventStream<G>() {
            @Override
            public <P> Map<G, P> project(Projection<P> projection) {

                Map<G, P> result = new HashMap<>();



                stream.forEach(e -> {
                    final G group = grouper.apply(e);
                    final P acc = result.computeIfAbsent(group, (x) -> projection.init());
                    projection.onEvent(acc, e);
                });



                return result;
            }
        };
    }

}
