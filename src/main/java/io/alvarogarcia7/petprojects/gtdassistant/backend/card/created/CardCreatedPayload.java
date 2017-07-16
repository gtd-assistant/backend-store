package io.alvarogarcia7.petprojects.gtdassistant.backend.card.created;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryPayload;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class CardCreatedPayload {
    public String name;
    public List<CategoryPayload> categories = new ArrayList<>();
}
