package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(exclude = {"id"}, callSuper = true)
@ToString
public class CardCreated extends Event {
    private final CardID id;
    private final String name;
    private final CategoryIds categoryIds;

    public CardCreated(String name, CategoryIds categoryIds) {
        super(EventID.random());
        this.id = CardID.random();
        this.name = name;
        this.categoryIds = categoryIds;
    }

    public CardCreated(String name) {
        this(name, CategoryIds.empty());
    }

    @EqualsAndHashCode
    @ToString
    @Setter
    @Getter
    public static class CardID {
        private final String id;

        public CardID(String id) {
            this.id = id;
        }

        public static CardID random() {
            return new CardID(UUID.randomUUID().toString());
        }

        public static CardID from(String value) {
            return new CardID(value);
        }
    }
}
