package com.pragmatists.domain;


public class Account {

    private final AccountId id;
    private String owner;
    private Integer number;

    private static int numbers = 100;
    private Integer balance = 0;

    public Account(AccountId id, String owner) {
        this.id = id;
        this.owner = owner;
        this.number = numbers++;

        EventQueue.publish(new AccountCreatedEvent(id, owner, number));
    }

    Account(AccountId id) {
        this.id = id;
    }

    public AccountId id() {
        return id;
    }

    public Integer balance() {
        return balance;
    }

    public String owner() {
        return owner;
    }

    public String number() {
        return "" + number;
    }

    public void deposit(Integer amount) {
        this.balance = this.balance + amount;
        EventQueue.publish(new AccountDepositedEvent(id, amount));
    }

    public void withdraw(Integer amount) {
        this.balance = this.balance - amount;
        EventQueue.publish(new AccountWithdrownEvent(id, amount));
    }

    @Override
    public String toString() {
        return String.format("%s: %d", id, balance);
    }

    public void replay(AccountCreatedEvent event) {


        this.owner = event.owner();
        this.number = event.number();
        System.err.println(String.format("Creating: %s, %s ", owner, number));
    }

    public void replay(AccountDepositedEvent event) {

        this.balance += event.amount();
        System.err.println(String.format("Deposited: %s, %s ", owner, number));
    }

    public void replay(AccountWithdrownEvent event) {

        this.balance -= event.amount();
        System.err.println(String.format("Withrown: %s, %s ", owner, number));
    }
}
