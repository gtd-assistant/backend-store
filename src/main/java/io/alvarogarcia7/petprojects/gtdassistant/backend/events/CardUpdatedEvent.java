package io.alvarogarcia7.petprojects.gtdassistant.backend.events;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardCreated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class CardUpdatedEvent extends Event {
    private final CardCreated.CardID cardID;
    private final String name;

    public CardUpdatedEvent(CardCreated.CardID cardID, String name) {
        super(EventID.random());
        this.cardID = cardID;
        this.name = name;
    }
}
