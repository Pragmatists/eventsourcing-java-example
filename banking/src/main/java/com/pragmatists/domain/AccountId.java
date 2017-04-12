package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.AggregateId;

import java.util.UUID;


public final class AccountId implements AggregateId {

    private final String value;

    private AccountId(String value) {
        this.value = value;
    }

    public static AccountId generate() {
        return new AccountId(UUID.randomUUID().toString());
    }

    public static AccountId from(String id) {
        return new AccountId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountId accountId = (AccountId) o;

        return value != null ? value.equals(accountId.value) : accountId.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
