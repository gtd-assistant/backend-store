package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode(of = {"name"})
@ToString
public class CardCreated extends Event {
    private final CardID id;
    private final String name;

    public CardCreated(String name) {
        super(EventID.random());
        this.id = CardID.random();
        this.name = name;
    }

    @EqualsAndHashCode
    @ToString
    public static class CardID {
        private final UUID id;

        private CardID(UUID id) {
            this.id = id;
        }

        public static CardID random() {
            return new CardID(UUID.randomUUID());
        }
    }
}
