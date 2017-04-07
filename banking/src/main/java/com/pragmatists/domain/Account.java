package com.pragmatists.domain;


public class Account {

	public final AccountId accountId;
	public String owner;
	public String number;
	public Integer balance;

	public Account(AccountId accountId) {
		this.accountId = accountId;
	}

	public void owner(String owner) {
		this.owner = owner;
	}

	public void deposit(int amount) {
		this.balance += amount;
	}
}
