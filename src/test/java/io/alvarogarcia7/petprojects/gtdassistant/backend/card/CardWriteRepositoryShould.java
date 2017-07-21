package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryId;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event;
import io.alvarogarcia7.petprojects.gtdassistant.backend.persistence.DataSourceTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class CardWriteRepositoryShould {

    private EventBus eventBus;
    private CardWriteRepository repository;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
        repository = Mockito.spy(new CardWriteRepository(new JdbcTemplate(new DataSourceTestConfig().dataSource())));
    }

    @Test
    public void respond_to_events_on_the_bus() {
        this.repository.registerOn(eventBus);

        eventBus.publish(CardCreatedObjectMother.sample());

        verify(this.repository).save(any(CardCreated.class));
    }


    @Test
    public void save_events_to_the_database() {

        Event.EventID eventId = this.repository.save(new CardCreated("buy milk", CategoryId.CategoryIds.empty()));

        assertThat(this.repository.findBy(eventId).isDefined()).isTrue();
    }

}
