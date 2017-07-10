package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CardCreatedEvent {
    String name;
}
