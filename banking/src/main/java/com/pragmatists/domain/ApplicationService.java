package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Command;
import com.pragmatists.eventsourcing.api.EventStore;

public class ApplicationService {

    private final EventStore eventStore;

    public ApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void handle(Command command){

    }

}
