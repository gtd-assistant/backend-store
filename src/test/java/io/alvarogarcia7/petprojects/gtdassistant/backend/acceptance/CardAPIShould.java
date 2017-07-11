package io.alvarogarcia7.petprojects.gtdassistant.backend.acceptance;

import io.alvarogarcia7.petprojects.gtdassistant.backend.DataSourceConfig;
import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.*;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.CardUpdatedEvent;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestAsyncSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CardAPIShould {
    EventBus eventBus;

    @Mock
    CardAdapter cardAdapter;

    @Before
    public void setUp(){
        eventBus = Mockito.mock(EventBus.class);
    }

    @Test
    public void publish_an_event_when_saving_a_card() {
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardsController(eventBus, cardAdapter))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"buy milkw\"}")
                .when();

        when.post("/api/v1/cards");

        verify(eventBus).publish(new CardCreated("buy milkw"));
    }

    @Test
    public void return_a_view_of_the_card_when_saving_a_card() {
        Mockito.doReturn(new CardDTO("1")).when(cardAdapter).adapt(Mockito.any(Card.class));
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardsController(eventBus, cardAdapter))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"buy milkb\"}")
                .when();

        MockMvcResponse request = when.post("/api/v1/cards");

        request.then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo("1"));
    }

    @Test
    public void rename_a_card() {
        String cardIdValue = UUID.randomUUID().toString();
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardsController(eventBus, cardAdapter))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"buy milks\"}")
                .when();

        MockMvcResponse request = when.post("/api/v1/rename/cards/"+cardIdValue);

        request.then()
                .statusCode(HttpStatus.OK.value());
        verify(eventBus).publish(new CardUpdatedEvent(CardCreated.CardID.from(cardIdValue), "buy milks"));
    }
}
