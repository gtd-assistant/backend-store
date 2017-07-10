package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import lombok.Getter;

@Getter
public class CardDTO {
    private final int id;

    public CardDTO(int id) {

        this.id = id;
    }
}
