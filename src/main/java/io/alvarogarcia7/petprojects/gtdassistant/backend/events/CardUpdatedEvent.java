package io.alvarogarcia7.petprojects.gtdassistant.backend.events;

public class CardUpdatedEvent extends Event {
    public CardUpdatedEvent() {
        super(EventID.random());
    }
}
