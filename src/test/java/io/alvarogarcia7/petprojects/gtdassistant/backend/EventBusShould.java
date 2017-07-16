package io.alvarogarcia7.petprojects.gtdassistant.backend;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryId;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventBusShould {

    private EventBus eventBus;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
    }

    @Test
    public void tell_subscribers_about_an_event() {
        X mock = mock(X.class);
        Consumer<CardCreated> y = mock::y;
        eventBus.subscribe(CardCreated.class, y);

        eventBus.publish(new CardCreated("hello", CategoryId.CategoryIds.empty()));

        verify(mock).y(any(CardCreated.class));
    }

    private class X {
        public <T> void y(CardCreated y) {

        }
    }
}
