package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

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
    String name;
    List<CategoryPayload> categories = new ArrayList<>();
}
