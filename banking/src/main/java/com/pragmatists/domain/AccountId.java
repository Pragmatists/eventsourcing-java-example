package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;

import java.util.UUID;


public class AccountId implements AggregateId {

    private String value;

    public AccountId(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    public static AccountId generate() {
        return new AccountId(UUID.randomUUID().toString());
    }
}
