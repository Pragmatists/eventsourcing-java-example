package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Command;
import com.pragmatists.eventsourcing.api.EventStore;

public class BankingService {

    private final EventStore eventStore;

    public BankingService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void handle(Command command){

    }

}
