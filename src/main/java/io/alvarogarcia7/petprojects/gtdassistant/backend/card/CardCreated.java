package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class CardCreated implements Event {
    private final String name;

    public CardCreated(String name) {
        this.name = name;
    }
}
