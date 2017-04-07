package com.pragmatists.domain;

public class AccountCreatedEvent extends AccountEvent{
    private final String owner;
    private final Integer number;

    public AccountCreatedEvent(AccountId id, String owner, Integer number) {
        super(id);
        this.owner = owner;
        this.number = number;
    }

    @Override
    public void applyOn(Account a) {
        a.replay(this);
    }

    public String owner() {
        return owner;
    }

    public Integer number() {
        return number;
    }
}
