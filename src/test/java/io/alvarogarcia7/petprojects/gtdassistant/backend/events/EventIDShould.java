package io.alvarogarcia7.petprojects.gtdassistant.backend.events;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event.EventID;
import org.junit.Test;

import java.util.UUID;

public class EventIDShould {

    @Test
    public void accept_any_string_as_a_valid_id() {
        EventID.aNew("a");
    }

    @Test
    public void accept_any_UUID_as_a_valid_id() {
        CardCreated.CardID.from(UUID.randomUUID().toString());
    }
}
