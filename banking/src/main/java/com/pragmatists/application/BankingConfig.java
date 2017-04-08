package com.pragmatists.application;


import com.pragmatists.domain.AccountRepository;
import com.pragmatists.domain.BankingService;
import com.pragmatists.eventsourcing.memory.InMemoryEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BankingConfig {

    @Bean
    BankingService bankingService(AccountRepository accountRepository) {
        return new BankingService(accountRepository);
    }
    
    @Bean
    AccountController accountController(BankingService bankingService, AccountRepository accountRepository) {
        return new AccountController(bankingService, accountRepository);
    }

    @Bean
    AccountRepository accountRepository() {
        return new AccountRepository(new InMemoryEventStore<>());
    }

}
