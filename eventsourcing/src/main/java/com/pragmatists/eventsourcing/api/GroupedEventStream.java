package com.pragmatists.eventsourcing.api;

import java.time.Month;
import java.util.Map;

public interface GroupedEventStream<G> {
    <P> Map<G, P> project(EventStream.Projection<P> projection);
}
