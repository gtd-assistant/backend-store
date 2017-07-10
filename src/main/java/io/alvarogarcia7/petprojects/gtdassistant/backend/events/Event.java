package io.alvarogarcia7.petprojects.gtdassistant.backend.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@EqualsAndHashCode
public class Event {

    @Getter
    private final EventID id;

    public Event(EventID id) {
        this.id = id;
    }

    @ToString
    @EqualsAndHashCode
    public static class EventID {

        @Getter
        private final UUID value;

        private EventID(UUID value) {
            this.value = value;
        }

        public static EventID aNew(String value) {
            return new EventID(UUID.fromString(value));
        }

        public static EventID random() {
            return new EventID(UUID.randomUUID());
        }
    }
}
