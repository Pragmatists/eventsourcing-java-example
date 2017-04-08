package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;

import java.util.UUID;


public class AccountId implements AggregateId {

    private String value;

    private AccountId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AccountId generate() {
        return new AccountId(UUID.randomUUID().toString());
    }

    public static AccountId of(String id) {
        return new AccountId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountId accountId = (AccountId) o;

        return getValue() != null ? getValue().equals(accountId.getValue()) : accountId.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }
}
