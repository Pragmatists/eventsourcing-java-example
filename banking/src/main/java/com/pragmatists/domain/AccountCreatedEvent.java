package com.pragmatists.domain;

import com.pragmatists.eventsourcing.api.Event;


public class AccountCreatedEvent implements Event<Account> {
	private final String owner;

	public AccountCreatedEvent(String owner) {
		this.owner = owner;
	}

	@Override
	public void applyTo(Account account) {
		account.owner(owner);
		account.balance=0;
		account.number = "123123";
	}
}
