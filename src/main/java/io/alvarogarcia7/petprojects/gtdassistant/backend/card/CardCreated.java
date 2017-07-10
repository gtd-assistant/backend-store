package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class CardCreated {
    private final String name;

    public CardCreated(String name) {
        this.name = name;
    }
}
