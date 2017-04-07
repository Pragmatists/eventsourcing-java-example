package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;

import java.util.UUID;


public class AccountId implements AggregateId {

    private String value;

    public AccountId(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static AccountId generate() {
        return new AccountId(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountId accountId = (AccountId) o;

        return value != null ? value.equals(accountId.value) : accountId.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
