package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryId;

import java.util.Arrays;

public class Card {
    private final String id;
    private final CategoryId.CategoryIds categoryIds;

    public Card() {
        this.id = "1";
        this.categoryIds = CategoryId.CategoryIds.of(Arrays.asList(CategoryId.of("1"), CategoryId.of("2")));
    }

    public CardDTO toDTO() {
        return new CardDTO(this.id, categoryIds.toDTO());
    }
}
