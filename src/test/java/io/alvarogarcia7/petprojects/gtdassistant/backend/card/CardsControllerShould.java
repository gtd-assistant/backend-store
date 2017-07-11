package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.CardUpdatedEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CardsControllerShould {

    @Mock
    EventBus eventBus;

    @Mock
    CardAdapter cardAdapter;

    @Test
    public void publish_an_event_when_renaming_a_card() {
        CardsController cardsController = new CardsController(eventBus, cardAdapter);
        String cardIdValue = UUID.randomUUID().toString();

        CardUpdatedPayload cardUpdatedPayload = new CardUpdatedPayload();
        cardUpdatedPayload.setName("buy milks");
        cardsController.cardUpdated(cardIdValue, cardUpdatedPayload);

        verify(eventBus).publish(new CardUpdatedEvent(CardCreated.CardID.from(cardIdValue), "buy milks"));
    }


}
