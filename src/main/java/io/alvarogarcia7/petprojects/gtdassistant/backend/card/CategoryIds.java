package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
public class CategoryIds {
    private final List<CategoryId> values;

    private CategoryIds(List<CategoryId> values) {
        this.values = values;
    }

    public static CategoryIds of(List<CategoryId> values) {
        return new CategoryIds(values);
    }

    public static CategoryIds empty() {
        return new CategoryIds(new ArrayList<>());
    }

    public List<CategoryIdDTO> toDTO() {
        return values.stream().map(this::toDTOCategoryId).collect(Collectors.toList());
    }

    public CategoryIdDTO toDTOCategoryId(CategoryId categoryId) {
        return categoryId.toDTO();
    }
}