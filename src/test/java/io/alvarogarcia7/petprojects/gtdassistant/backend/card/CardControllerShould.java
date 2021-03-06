package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated.CardID;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreatedPayload;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.updated.CardUpdatedPayload;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.CardUpdatedEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CardControllerShould {

    @Mock
    EventBus eventBus;

    @Mock
    CardAdapter cardAdapter;
    private CardController cardController;

    @Before
    public void setUp() throws Exception {
        cardController = new CardController(eventBus, cardAdapter);
    }

    @Test
    public void publish_an_event_when_saving_a_card() {

        cardController.cardCreated(cardCreatedPayload("buy milk"));

        verify(eventBus).publish(new CardCreated("buy milk"));
    }

    @Test
    public void publish_an_event_when_renaming_a_card() {
        String cardIdValue = "any-card";

        cardController.cardUpdated(cardIdValue, cardUpdatedPayload("buy milks"));

        verify(eventBus).publish(new CardUpdatedEvent(CardID.from(cardIdValue), "buy milks"));
    }


    private CardUpdatedPayload cardUpdatedPayload(String name) {
        CardUpdatedPayload cardUpdatedPayload = new CardUpdatedPayload();
        cardUpdatedPayload.setName(name);
        return cardUpdatedPayload;
    }

    private CardCreatedPayload cardCreatedPayload(String name) {
        CardCreatedPayload cardCreatedPayload = new CardCreatedPayload();
        cardCreatedPayload.setName(name);
        return cardCreatedPayload;
    }


}
