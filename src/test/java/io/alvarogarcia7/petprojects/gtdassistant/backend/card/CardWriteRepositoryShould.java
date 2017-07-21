package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class CardWriteRepositoryShould {

    private EventBus eventBus;
    private CardWriteRepository repository;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
        repository = Mockito.spy(new CardWriteRepository());
    }

    @Test
    public void respond_to_events_on_the_bus() {
        CardWriteRepository repository = this.repository;
        repository.registerOn(eventBus);

        eventBus.publish(CardCreatedObjectMother.sample());

        verify(repository).save(any(CardCreated.class));
    }

}
