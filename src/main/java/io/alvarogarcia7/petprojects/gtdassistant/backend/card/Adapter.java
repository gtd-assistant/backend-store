package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import java.util.List;
import java.util.stream.Collectors;

public class Adapter {
    public CardCreated adapt(CardCreatedPayload payload) {
        List<CategoryId> categoryIds = payload.categories.stream().map(this::adapt).collect(Collectors.toList());
        return new CardCreated(payload.name, CategoryIds.of(categoryIds));
    }

    public CategoryId adapt(CategoryPayload payload) {
        return CategoryId.of(payload.id);
    }
}
