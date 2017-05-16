package com.pragmatists.eventsourcing.api;


import java.util.List;
import java.util.function.Function;

public interface EventStream extends Iterable<Event> {
    long version();

    void addAll(List<Event> changes);

    <P> P project(Projection<P> projection);

    <G> GroupedEventStream<G> groupBy(Function<Event, G> grouper);

    interface Projection<P> {

        public void onEvent(P projection, Event e);

        P init();

    }
}
