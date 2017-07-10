package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.CardUpdatedEvent;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {

    private final EventBus eventBus;

    private final CardAdapter cardAdapter;

    public CardsController(EventBus eventBus, CardAdapter cardAdapter) {
        this.eventBus = eventBus;
        this.cardAdapter = cardAdapter;
    }

    @PostMapping(value = "/api/v1/cards", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> cardCreated(@RequestBody CardCreatedEvent event) {
        eventBus.publish(new CardCreated(event.getName()));
        return ResponseEntity.ok(cardAdapter.adapt(new Card()));
    }

    //TODO AGB: Find out how to solve this
    @PostMapping(value = "/api/v1/cards?action=rename", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cardCreated(@RequestBody CardUpdatedEvent event) {
        eventBus.publish(event);
        return ResponseEntity.ok().build();
    }
}
