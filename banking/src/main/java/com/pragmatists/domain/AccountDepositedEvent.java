package com.pragmatists.domain;

public class AccountDepositedEvent extends AccountEvent {

    private final Integer amount;

    public AccountDepositedEvent(AccountId id, Integer amount) {
        super(id);
        this.amount = amount;
    }

    @Override
    public void applyOn(Account a) {
        a.replay(this);
    }

    public Integer amount() {
        return amount;
    }
}
