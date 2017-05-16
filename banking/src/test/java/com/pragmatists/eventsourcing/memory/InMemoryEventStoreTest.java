package com.pragmatists.eventsourcing.memory;

import com.pragmatists.domain.AccountDeposited;
import com.pragmatists.domain.AccountId;
import com.pragmatists.domain.AccountWithdrawn;
import com.pragmatists.eventsourcing.api.Event;
import com.pragmatists.eventsourcing.api.EventStream;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryEventStoreTest {

    private InMemoryEventStore store = new InMemoryEventStore();

    @Test
    public void sampleBankBalanceProjection() throws Exception {
        // GIVEN
        final Event acc1Event1 = new AccountWithdrawn(AccountId.from("1"), 123, LocalDate.parse("2016-10-05"));
        final Event acc1Event2 = new AccountWithdrawn(AccountId.from("1"), 100, LocalDate.parse("2016-10-05"));
        final Event acc2Event1 = new AccountDeposited(AccountId.from("2"), 1_000_100, LocalDate.parse("2016-10-05"));
        final Event acc2Event2 = new AccountDeposited(AccountId.from("2"), 1, LocalDate.parse("2016-10-05"));
        final Event acc3Event1 = new AccountDeposited(AccountId.from("3"), 100_000_000, LocalDate.parse("2016-10-05"));
        store.store(AccountId.from("1"), 0, Arrays.asList(acc1Event1, acc1Event2));
        store.store(AccountId.from("2"), 0, Arrays.asList(acc2Event1, acc2Event2));
        store.store(AccountId.from("3"), 0, Arrays.asList(acc3Event1));

        // WHEN
        BankBalance result = store.loadEventStream(allEvents()).project(new BankBalanceProjection());

        // THEN
        assertThat(result.deposited).isEqualTo(101_000_101);
        assertThat(result.withdrawn).isEqualTo(223);
    }


    @Test
    public void sampleBalancePerMonthProjection() throws Exception {
        // GIVEN

        final Event acc1Event1 = new AccountWithdrawn(AccountId.from("1"), 123, LocalDate.parse("2016-05-05"));
        final Event acc1Event2 = new AccountWithdrawn(AccountId.from("1"), 100, LocalDate.parse("2016-10-05"));
        final Event acc2Event1 = new AccountDeposited(AccountId.from("2"), 1_000_100, LocalDate.parse("2016-05-05"));
        final Event acc2Event2 = new AccountDeposited(AccountId.from("2"), 1, LocalDate.parse("2016-10-05"));
        final Event acc3Event1 = new AccountDeposited(AccountId.from("3"), 100_000_000, LocalDate.parse("2016-05-05"));
        store.store(AccountId.from("1"), 0, Arrays.asList(acc1Event1, acc1Event2));
        store.store(AccountId.from("2"), 0, Arrays.asList(acc2Event1, acc2Event2));
        store.store(AccountId.from("3"), 0, Arrays.asList(acc3Event1));

        // WHEN
        Map<Month, BalancePerMonth> result = store.loadEventStream(allEvents())
                .groupBy(evt -> evt.getDate().getMonth())
                .project(new BalancePerMonthProjection());

        // THEN
        assertThat(result)
                .containsEntry(Month.MAY, new BalancePerMonth(100999977))
                .containsEntry(Month.OCTOBER, new BalancePerMonth(-99));
    }

    class BankBalance {

        public int deposited;
        public int withdrawn;
    }

    private class BankBalanceProjection implements EventStream.Projection<BankBalance> {

        @Override
        public void onEvent(BankBalance acc, Event e) {
            if (e instanceof AccountWithdrawn) {
                acc.withdrawn += ((AccountWithdrawn) e).getAmount();
            } else if (e instanceof AccountDeposited) {
                acc.deposited += ((AccountDeposited) e).getAmount();
            }
        }

        @Override
        public BankBalance init() {
            return new BankBalance();
        }
    }

    private class BalancePerMonth {

        public int balance;

        public BalancePerMonth(int balance) {
            this.balance = balance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BalancePerMonth that = (BalancePerMonth) o;
            return balance == that.balance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(balance);
        }

    }

    private class BalancePerMonthProjection implements EventStream.Projection<BalancePerMonth> {

        @Override
        public void onEvent(BalancePerMonth acc, Event e) {
            if (e instanceof AccountWithdrawn) {
                acc.balance -= ((AccountWithdrawn) e).getAmount();
            } else if (e instanceof AccountDeposited) {
                acc.balance += ((AccountDeposited) e).getAmount();
            }
        }

        @Override
        public BalancePerMonth init() {
            return new BalancePerMonth(0);
        }

    }

    private Predicate<Event> allEvents() {
        return (evt) -> true;
    }
}