package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryId;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;

public class CardCreatedObjectMother {
    public static CardCreated sample() {
        return new CardCreated("", CategoryId.CategoryIds.empty());
    }
}
