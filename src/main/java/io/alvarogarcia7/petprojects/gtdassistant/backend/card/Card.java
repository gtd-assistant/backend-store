package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

public class Card {
    private final int id;

    public Card() {
        this.id = 1;
    }

    public CardDTO toDTO() {
        return new CardDTO(this.id);
    }
}