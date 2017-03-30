package com.pragmatists.eventsourcing.api;

import java.util.UUID;

public class AggregateId {
    private String value;

    AggregateId(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID().toString());
    }

}
