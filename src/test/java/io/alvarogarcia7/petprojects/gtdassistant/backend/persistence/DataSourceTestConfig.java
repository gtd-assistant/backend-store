package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceTestConfig {

    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.aNewH2().loadProductionMigrations().loadTestMigrations().build();
    }
}
