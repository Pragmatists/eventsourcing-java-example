package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Event;

public class AccountDepositEvent implements Event<Account> {
	private final int amount;

	public AccountDepositEvent(int amount) {
		this.amount = amount;
	}

	@Override
	public void applyTo(Account account) {
		account.deposit(amount);
	}
}
