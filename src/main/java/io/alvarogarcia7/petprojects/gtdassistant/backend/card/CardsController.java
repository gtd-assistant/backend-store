package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    private final CardWriteRepository cardWriteRepository;

    private final CardAdapter cardAdapter;

    public CardsController(CardWriteRepository cardWriteRepository, CardAdapter cardAdapter) {
        this.cardWriteRepository = cardWriteRepository;
        this.cardAdapter = cardAdapter;
    }

    @PostMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> cardCreated(@RequestBody CardCreatedEvent event) {
        cardWriteRepository.save(new CardCreated(event.getName()));
        return ResponseEntity.ok(cardAdapter.adapt(new Card()));
    }
}
