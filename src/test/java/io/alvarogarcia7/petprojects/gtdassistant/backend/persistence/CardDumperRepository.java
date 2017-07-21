package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@Ignore
public class CardDumperRepository {
    @Test
    public void dump_contents_of_the_events_table() {

        CardDumpRepository repository = new CardDumpRepository(new JdbcTemplate(new DataSourceTestConfig().dataSource()));

        List<Map<String, Object>> result = repository.dumpAll();

        result.forEach(System.out::println);
    }
}
