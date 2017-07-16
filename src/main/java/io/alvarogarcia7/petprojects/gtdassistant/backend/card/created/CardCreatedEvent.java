package io.alvarogarcia7.petprojects.gtdassistant.backend.card.created;

import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class CardCreatedEvent extends Event {
    private final String name;

    public CardCreatedEvent(String name) {
        super(EventID.random());
        this.name = name;
    }
}
