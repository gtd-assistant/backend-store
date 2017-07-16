package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryId;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class CardWriteRepositoryShould {

    private EventBus eventBus;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
    }

    @Test
    public void respond_to_events_on_the_bus() {
        CardWriteRepository repository = Mockito.spy(new CardWriteRepository());
        repository.registerOn(eventBus);

        eventBus.publish(new CardCreated("", CategoryId.CategoryIds.empty()));

        verify(repository).save(any(CardCreated.class));
    }
}
