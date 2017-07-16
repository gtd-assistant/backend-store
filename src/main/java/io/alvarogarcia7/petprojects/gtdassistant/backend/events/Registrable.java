package io.alvarogarcia7.petprojects.gtdassistant.backend.events;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;

public interface Registrable {
    void registerOn(EventBus eventBus);
}
