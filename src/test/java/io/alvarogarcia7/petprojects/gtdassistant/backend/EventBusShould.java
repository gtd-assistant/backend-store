package io.alvarogarcia7.petprojects.gtdassistant.backend;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardCreatedObjectMother;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class EventBusShould {

    private EventBus eventBus;
    private CardCreatedSubscriber mock;
    private CardCreatedSubscriber mock2;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
        mock = mock(CardCreatedSubscriber.class);
        mock2 = mock(CardCreatedSubscriber.class);
    }

    @Test
    public void tell_a_subscriber_about_an_event() {
        eventBus.subscribe(CardCreated.class, mock::process);

        eventBus.publish(sample());

        verify(mock).process(any(CardCreated.class));
    }

    private CardCreated sample() {
        return CardCreatedObjectMother.sample();
    }

    @Test
    public void tell_all_subscribers_about_an_event() {
        eventBus.subscribe(CardCreated.class, mock::process);
        eventBus.subscribe(CardCreated.class, mock2::process);

        eventBus.publish(sample());

        verify(mock).process(any(CardCreated.class));
        verify(mock2).process(any(CardCreated.class));
    }

    @Test
    public void tell_the_same_subscriber_multiple_times_about_the_same_event() {
        // is this a feature or a defect?
        // for now, documenting the behavior
        eventBus.subscribe(CardCreated.class, mock::process);
        eventBus.subscribe(CardCreated.class, mock::process);

        eventBus.publish(sample());

        verify(mock,times(2)).process(any(CardCreated.class));
    }

    private class CardCreatedSubscriber {
        public void process(CardCreated y) {

        }
    }
}
