package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import lombok.Getter;

@Getter
public class CardDTO {
    private final String id;

    public CardDTO(String id) {
        this.id = id;
    }
}
