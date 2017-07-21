package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class CardDumpRepository {
    private final JdbcTemplate jdbcTemplate;

    public CardDumpRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void printAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT ID, EVENT_TYPE, EVENT FROM EVENTS");
        maps.forEach(resultSet -> {
            String id = (String) resultSet.get("id");
            String eventType = (String) resultSet.get("event_type");
            System.out.println(id);
            System.out.println(eventType);
        });
    }
}
