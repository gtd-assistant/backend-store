package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryId;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event.EventID;
import io.alvarogarcia7.petprojects.gtdassistant.backend.persistence.CardDumpRepository;
import io.alvarogarcia7.petprojects.gtdassistant.backend.persistence.CardDumperRepository;
import io.alvarogarcia7.petprojects.gtdassistant.backend.persistence.DataSourceTestConfig;
import io.vavr.control.Option;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class CardWriteRepositoryShould {

    private EventBus eventBus;
    private CardWriteRepository repository;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
        jdbcTemplate = new JdbcTemplate(new DataSourceTestConfig().testDataSource());
        repository = Mockito.spy(new CardWriteRepository(jdbcTemplate));
    }

    @Test
    public void respond_to_events_on_the_bus() {
        this.repository.registerOn(eventBus);

        eventBus.publish(CardCreatedObjectMother.sample());

        verify(this.repository).save(any(CardCreated.class));
    }


    @Test
    public void save_events_to_the_database() {
        CardCreated event = new CardCreated("buy milk", CategoryId.CategoryIds.empty());

        EventID eventId = this.repository.save(event);

        assertThat(this.repository.exists(eventId).isDefined()).isTrue();

        listContents();
    }

    @Test
    public void find_events_in_the_database() {
        String existingIdInDB = "10";
        Option<EventID> id = this.repository.exists(EventID.aNew(existingIdInDB));

        assertThat(id.isDefined()).isTrue();
    }

    private void listContents() {
        List<Map<String, Object>> x = new CardDumpRepository(jdbcTemplate).dumpAll();
        x.forEach(System.out::println);
    }

}
