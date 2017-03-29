package com.pragmatists.eventsourcing.api.store;


import java.util.UUID;

public interface Command {
    UUID aggregateId();
}
