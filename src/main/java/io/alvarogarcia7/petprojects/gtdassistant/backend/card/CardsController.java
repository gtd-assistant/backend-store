package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<CardDTO> cardCreated() {
        cardWriteRepository.save(new CardCreated("buy milk"));
        return ResponseEntity.ok(cardAdapter.adapt(new Card()));
    }
}
