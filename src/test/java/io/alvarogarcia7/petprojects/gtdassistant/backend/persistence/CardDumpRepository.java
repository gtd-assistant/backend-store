package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class CardDumpRepository {
    private final JdbcTemplate jdbcTemplate;

    public CardDumpRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> dumpAll() {
        return jdbcTemplate.queryForList("SELECT ID, EVENT_TYPE, EVENT FROM EVENTS");
    }
}
