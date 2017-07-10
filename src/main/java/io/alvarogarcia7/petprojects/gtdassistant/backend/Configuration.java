package io.alvarogarcia7.petprojects.gtdassistant.backend;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardAdapter;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public CardAdapter cardAdapter(){
        return new CardAdapter();
    }

    @Bean
    public EventBus eventBus(){
        return new EventBus();
    }


}
