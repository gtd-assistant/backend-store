package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

public interface CardWriteRepository {
    void save(CardCreated event);
}
