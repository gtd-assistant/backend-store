package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration("datasource-test")
public class DataSourceTestConfig {

    @Bean
    public DataSource testDataSource(){
        return dataSource();
    }

    private DataSource dataSource(){
        return DataSourceBuilder.aNewH2().loadProductionMigrations().loadTestMigrations().build();
    }

    @Bean(initMethod = "migrate")
    public Flyway performMigrations(){
        return performMigrationsOn(testDataSource());
    }

    public Flyway performMigrationsOn (final DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:db/migration/");
        flyway.setDataSource(dataSource);
//        flyway.setBaselineVersion(MigrationVersion.fromVersion("1.0.4.2"));
//        flyway.baseline();
        flyway.migrate();
        return flyway;
    }

    private DataSource serverDataSource()  {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:tcp://localhost/~/test");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("");
        return jdbcDataSource;
    }
}
