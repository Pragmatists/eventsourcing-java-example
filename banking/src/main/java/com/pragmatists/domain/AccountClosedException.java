package com.pragmatists.domain;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@ResponseStatus(NOT_FOUND)
class AccountClosedException extends RuntimeException {

    AccountClosedException(AccountId accountId) {
        super("account closed: '" + accountId + "'.");
    }
}
