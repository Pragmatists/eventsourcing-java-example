package com.pragmatists.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EventQueue {

    private static ThreadLocal<List<AccountEvent>> events = ThreadLocal.withInitial(() -> new ArrayList<>());
//    private static ThreadLocal<List<AccountEvent>> events = ThreadLocal.withInitial(() -> new ArrayList<>());

    public static void publish(AccountEvent event) {
        events.get().add(event);
    }

    public static Stream<AccountEvent> events() {
        return events.get().stream();
    }

    public static void clear() {
        events.get().clear();
    }
}
