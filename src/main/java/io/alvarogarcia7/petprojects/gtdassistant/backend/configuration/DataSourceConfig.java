package io.alvarogarcia7.petprojects.gtdassistant.backend.configuration;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration("datasource")
public class DataSourceConfig {


    @Bean
    public DataSource testDataSource(){
        return serverDataSource();
    }

//    private DataSource dataSource(){
//        return DataSourceBuilder.aNewH2().loadProductionMigrations().loadTestMigrations().build();
//    }

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
