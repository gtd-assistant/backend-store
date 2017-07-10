package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardCreated.CardID;
import static io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardCreated.EventID;

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
        eventBus.publish(new CardCreated(EventID.random(), CardID.random(), event.getName()));
        return ResponseEntity.ok(cardAdapter.adapt(new Card()));
    }
}
