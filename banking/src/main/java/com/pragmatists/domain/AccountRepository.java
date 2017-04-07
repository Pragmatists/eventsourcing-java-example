package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;

import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class AccountRepository {

	private EventStore<Event> eventStore;

	public AccountRepository(EventStore<Event> eventStore) {

		this.eventStore = eventStore;
	}

	public void create(AccountId accountId, String owner) {
		Account account = new Account(accountId);
		AccountCreatedEvent accountCreatedEvent = new AccountCreatedEvent(owner);
		accountCreatedEvent.applyTo(account);
		eventStore.store(accountId, asList(accountCreatedEvent));
	}

	public Account load(AccountId accountId) {

		Account account = new Account(accountId);
		Stream<Event> events = eventStore.loadEventStream(accountId);

		events.forEach(event -> {
			event.applyTo(account);
		});

		return account;
	}

	public void deposit(AccountId accountId, int amount) {
		eventStore.store(accountId, asList(new AccountDepositEvent(amount)));
	}
}
