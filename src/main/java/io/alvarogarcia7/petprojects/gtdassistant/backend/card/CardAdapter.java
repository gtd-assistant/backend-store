package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

public class CardAdapter {
    public CardDTO adapt(Card card) {
        return card.toDTO();
    }
}
