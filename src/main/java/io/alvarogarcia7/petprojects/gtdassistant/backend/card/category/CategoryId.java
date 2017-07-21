package io.alvarogarcia7.petprojects.gtdassistant.backend.card.category;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    @EqualsAndHashCode
    @ToString
    public static class CategoryIds {
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
            return values.stream().map(this::toDTOCategoryId).collect(toList());
        }

        public CategoryIdDTO toDTOCategoryId(CategoryId categoryId) {
            return categoryId.toDTO();
        }
    }
}
