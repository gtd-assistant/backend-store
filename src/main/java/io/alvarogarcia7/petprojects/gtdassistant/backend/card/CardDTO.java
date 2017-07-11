package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import lombok.Getter;

import java.util.List;

@Getter
public class CardDTO {
    private final String id;
    private final List<CategoryIdDTO> categoryIds;

    public CardDTO(String id, List<CategoryIdDTO> categoryIds) {
        this.id = id;
        this.categoryIds = categoryIds;
    }
}
