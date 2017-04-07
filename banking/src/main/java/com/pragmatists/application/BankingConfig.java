package com.pragmatists.application;


import com.pragmatists.domain.AccountRepository;
import com.pragmatists.domain.BankingService;
import com.pragmatists.eventsourcing.memory.InMemoryEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BankingConfig {

    @Bean
    BankingService bankingService(){
        AccountRepository accountRepository = new AccountRepository(new InMemoryEventStore<>());
        return new BankingService(accountRepository);
    }
    @Bean
    AccountController accountController(){
        return new AccountController(bankingService());
    }

}
