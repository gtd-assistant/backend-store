package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Event;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.Registrable;
import io.vavr.control.Option;
import org.springframework.jdbc.core.JdbcTemplate;

public class CardWriteRepository implements Registrable {

    private final JdbcTemplate jdbcTemplate;

    public CardWriteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registerOn(EventBus eventBus) {
        eventBus.subscribe(CardCreated.class, this::save);
    }

    public Event.EventID save(CardCreated event) {
        Event.EventID eventId = event.getId();
        this.jdbcTemplate.update("INSERT INTO EVENTS (ID, EVENT_TYPE, EVENT) VALUES (?, ?, ?)",
                new Object[]{
                        eventId.getValue(),
                        CardCreated.class.getSimpleName(),
                        "hello"
                });
        return eventId;
    }

    public Option<Event.EventID> findBy(Event.EventID eventId) {
        return jdbcTemplate.queryForObject("SELECT ID, EVENT_TYPE, EVENT FROM EVENTS WHERE ID = ?", (resultSet, i) -> {
            String id = resultSet.getString("id");
            return Option.of(Event.EventID.aNew(id));
        }, eventId.getValue());
    }
}
