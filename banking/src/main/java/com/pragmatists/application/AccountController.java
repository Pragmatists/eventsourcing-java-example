package com.pragmatists.application;

import com.pragmatists.domain.*;
import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStore;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Controller
@RequestMapping("/account")
class AccountController {

    private final BankingService bankingService;
    private final EventStore eventStore;

    AccountController(BankingService bankingService, EventStore eventStore) {
        this.bankingService = bankingService;
        this.eventStore = eventStore;
    }

    @PostMapping
    ResponseEntity<?> createAccount(@RequestParam String owner) {

        AccountId id = null;
        try {
            EventQueue.clear();

            id = bankingService.newAccount(owner);

            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return created(location).build();
        } finally {
            flush(id);
        }

    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountResource> getAccount(@PathVariable String id) {

        EventQueue.clear();
        Optional<Account> account = bankingService.load(AccountId.from(id));

        return account
                .map(AccountResource::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}/withdraw")
    void withdraw(@PathVariable String id, @RequestParam String amount, @RequestParam String accountId) {
        EventQueue.clear();
        bankingService.withdraw(AccountId.from(id), AccountId.from(accountId), Integer.valueOf(amount));
    }

    @PutMapping(path = "/{id}/deposit")
    void deposit(@PathVariable String id, @RequestParam String amount) {
        EventQueue.clear();
        bankingService.deposit(AccountId.from(id), Integer.valueOf(amount));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> closeAccount(@PathVariable String id) {
        EventQueue.clear();
        bankingService.closeAccount(AccountId.from(id));

        return noContent().build();
    }


    public void flush(AccountId id) {
        List<Event> newEvents = EventQueue
                .events()
                .filter(accountWithId(id))
                .collect(Collectors.toList());

        eventStore.store(id, 0, newEvents);

    }

    private Predicate<AccountEvent> accountWithId(AccountId accountId) {
        return account -> account.id().equals(accountId);
    }


}
