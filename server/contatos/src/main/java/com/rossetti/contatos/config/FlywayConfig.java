package com.rossetti.contatos.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class FlywayConfig {

    @Bean(initMethod = "migrate")
    public Flyway flyway(){
        Flyway flyway = Flyway.configure()
                .locations("classpath:db/migration")
                .dataSource("jdbc:postgresql://localhost:5432/Contatos", "postgres", "123qwe")
                .load();

        flyway.migrate();

        return flyway;
    }
}
