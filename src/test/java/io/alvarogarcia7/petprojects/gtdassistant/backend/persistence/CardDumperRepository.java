package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class CardDumperRepository {
    @Test
    public void dump_contents_of_the_events_table() {

        CardDumpRepository repository = new CardDumpRepository(new JdbcTemplate(new DataSourceTestConfig().dataSource()));

        List<Map<String, Object>> result = repository.dumpAll();

        result.forEach(row -> {
            String id = (String) row.get("id");
            String eventType = (String) row.get("event_type");
            System.out.println(id);
            System.out.println(eventType);
        });
    }
}
