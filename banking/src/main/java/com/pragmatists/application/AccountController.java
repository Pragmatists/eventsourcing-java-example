package com.pragmatists.application;

import com.pragmatists.domain.Account;
import com.pragmatists.domain.AccountId;
import com.pragmatists.domain.AccountRepository;
import com.pragmatists.domain.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Controller
@RequestMapping("/account")
class AccountController {


    private final BankingService bankingService;

    private final AccountRepository accountRepository;

    @Autowired
    AccountController(BankingService bankingService, AccountRepository accountRepository) {
        this.bankingService = bankingService;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    ResponseEntity<?> createAccount(@RequestParam String owner) {
        AccountId id = AccountId.generate();
        Account account = new Account(id, owner);
        accountRepository.store(account);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(id.getValue()).toUri();
        return created(location).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountResource> getAccount(@PathVariable String id) {
        Account account = accountRepository.load(AccountId.of(id));
        if (account.isClosed()) {
            return notFound().build();
        }
        AccountResource accountResource = new AccountResource(id, "", account.owner(), account.balance().toString());
        return ok(accountResource);
    }

    @PutMapping(path = "/{id}/withdraw")
    void withdraw(@PathVariable String id, @RequestParam String amount) {
        Account account = accountRepository.load(AccountId.of(id));
        account.withdraw(new BigDecimal(amount));
        accountRepository.store(account);
    }

    @PutMapping(path = "/{id}/deposit")
    void deposit(@PathVariable String id, @RequestParam String amount) {
        Account account = accountRepository.load(AccountId.of(id));
        account.deposit(new BigDecimal(amount));
        accountRepository.store(account);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> closeAccount(@PathVariable String id) {
        Account account = accountRepository.load(AccountId.of(id));
        account.close();
        accountRepository.store(account);
        return noContent().build();
    }


}
