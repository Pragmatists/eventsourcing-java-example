package com.pragmatists.domain;


import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountRepository {

//    private List<Account> accounts = new ArrayList<>();
    private EventStore eventStore;

    public AccountRepository(EventStore eventStore) {

        this.eventStore = eventStore;
    }

    public void store(Account account) {

        List<Event> newEvents = EventQueue
                .events()
                .filter(accountWithId(account.id()))
                .collect(Collectors.toList());

        eventStore.store(account.id(), 0, newEvents);
    }

    public void remove(AccountId accountId) {
//        accounts.removeIf(accountWithId(accountId));
    }

    private Predicate<AccountEvent> accountWithId(AccountId accountId) {
        return account -> account.id().equals(accountId);
    }

    public Optional<Account> load(AccountId id) {

        Account a = new Account(id);
        eventStore.loadEventStream(id).forEach(e -> {
            AccountEvent ae = (AccountEvent) e;
            ae.applyOn(a);
        });

        return Optional.of(a);
    }

}
