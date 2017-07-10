package io.alvarogarcia7.petprojects.gtdassistant.backend;

import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardAdapter;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardWriteRepository;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public CardWriteRepository cardWriteRepository(){
        return new CardWriteRepository() {
            @Override
            public void save(CardCreated event) {

            }
        };
    }

    @Bean
    public CardAdapter cardAdapter(){
        return new CardAdapter();
    }


}
