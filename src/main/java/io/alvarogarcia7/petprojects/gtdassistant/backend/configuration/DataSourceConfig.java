package io.alvarogarcia7.petprojects.gtdassistant.backend.configuration;

import io.alvarogarcia7.petprojects.gtdassistant.backend.persistence.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration("datasource")
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.aNewH2().loadProductionMigrations().build();
    }
}
