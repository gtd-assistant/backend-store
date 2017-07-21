package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class CardDumperRepository {
    @Test
    public void dump_contents_of_the_events_table() {

        CardDumpRepository repository = new CardDumpRepository(new JdbcTemplate(new DataSourceTestConfig().dataSource()));

        repository.printAll();
    }
}
