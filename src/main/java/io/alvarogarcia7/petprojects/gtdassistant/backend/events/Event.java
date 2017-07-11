package io.alvarogarcia7.petprojects.gtdassistant.backend.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString(exclude = {"id"})
@EqualsAndHashCode(exclude = {"id"})
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
        private final String value;

        private EventID(String value) {
            this.value = value;
        }

        public static EventID aNew(String value) {
            return new EventID(value);
        }

        public static EventID random() {
            return new EventID(UUID.randomUUID().toString());
        }
    }
}
