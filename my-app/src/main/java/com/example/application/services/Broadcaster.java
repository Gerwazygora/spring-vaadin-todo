package com.example.application.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class Broadcaster {

    private static final List<Consumer<String>> listeners = new CopyOnWriteArrayList<>();

    public static void register(Consumer<String> listener) {
        listeners.add(listener);
    }

    public static void unregister(Consumer<String> listener) {
        listeners.remove(listener);
    }

    public static void broadcast(String message) {
        for (Consumer<String> listener : listeners) {
            listener.accept(message);
        }
    }
}