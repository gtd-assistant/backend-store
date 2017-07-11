package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class CategoryId {
    private final String value;

    private CategoryId(String value) {
        this.value = value;
    }

    public static CategoryId of(String id) {
        return new CategoryId(id);
    }

    public CategoryIdDTO toDTO() {
        CategoryIdDTO categoryIdDTO = new CategoryIdDTO();
        categoryIdDTO.id = value;
        return categoryIdDTO;
    }
}
