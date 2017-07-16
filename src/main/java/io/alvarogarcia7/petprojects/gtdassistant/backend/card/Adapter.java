package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryId;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryPayload;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreatedPayload;

import java.util.List;
import java.util.stream.Collectors;

public class Adapter {
    public CardCreated adapt(CardCreatedPayload payload) {
        List<CategoryId> categoryIds = payload.categories.stream().map(this::adapt).collect(Collectors.toList());
        return new CardCreated(payload.name, CategoryId.CategoryIds.of(categoryIds));
    }

    public CategoryId adapt(CategoryPayload payload) {
        return CategoryId.of(payload.id);
    }
}
