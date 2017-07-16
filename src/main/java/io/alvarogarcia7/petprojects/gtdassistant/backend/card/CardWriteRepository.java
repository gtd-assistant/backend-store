package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Registrable;

public class CardWriteRepository implements Registrable {

    @Override
    public void registerOn(EventBus eventBus) {
        eventBus.subscribe(CardCreated.class, this::save);
    }

    public void save(CardCreated event){

    }
}
