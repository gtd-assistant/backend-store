package io.alvarogarcia7.petprojects.gtdassistant.backend;

import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;


public class EventBus {
    private final MultiValueMap<Class, Consumer> subscribers = new LinkedMultiValueMap<>();

    public void publish(Event event) {
        subscribers
                .getOrDefault(event.getClass(), Collections.emptyList())
                .forEach(callback -> callback.accept(event));
    }

    public <T> void subscribe(Class<T> event, Consumer<T> callback) {
        this.subscribers
                .computeIfAbsent(event, (x) -> new ArrayList<>())
                .add(callback);
    }
}
