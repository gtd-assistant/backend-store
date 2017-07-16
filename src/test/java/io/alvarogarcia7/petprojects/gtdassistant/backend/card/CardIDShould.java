package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import org.junit.Test;

import java.util.UUID;

public class CardIDShould {

    @Test
    public void accept_any_string_as_a_valid_id() {
        CardCreated.CardID.from("a");
    }

    @Test
    public void accept_any_UUID_as_a_valid_id() {
        CardCreated.CardID.from(UUID.randomUUID().toString());
    }

}
