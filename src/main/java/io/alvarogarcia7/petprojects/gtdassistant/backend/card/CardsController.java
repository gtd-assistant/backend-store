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
    public ResponseEntity<CardDTO> cardCreated(@RequestBody CardCreatedPayload cardCreatedPayload) {
//        if(cardCreatedPayload.equals("forbidden")){
            throw new SlangPayloadException();
//        }
//        eventBus.publish(new Adapter().adapt(cardCreatedPayload));
//        return ResponseEntity.ok(cardAdapter.adapt(new Card()));
    }

    //TODO AGB: Find out how to solve this
    @PutMapping(value = "/api/v1/rename/cards/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cardUpdated(@PathVariable("cardId") String cardIdValue, @RequestBody CardUpdatedPayload event) {
        eventBus.publish(new CardUpdatedEvent(CardCreated.CardID.from(cardIdValue), event.getName()));
        return ResponseEntity.ok().build();
    }


}

