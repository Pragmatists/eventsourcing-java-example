package com.pragmatists.eventsourcing.api;


public interface Event<V> {
	void applyTo(V aggregate);
}
