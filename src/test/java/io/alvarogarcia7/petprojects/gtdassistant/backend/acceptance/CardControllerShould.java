package io.alvarogarcia7.petprojects.gtdassistant.backend.acceptance;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.*;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryIdDTO;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.CardUpdatedEvent;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestAsyncSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static java.util.Arrays.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class CardControllerShould {
    @Mock
    EventBus eventBus;

    @Mock
    CardAdapter cardAdapter;

    @Test
    public void save_a_card() {
        Mockito.doReturn(new CardDTO("1", asList())).when(cardAdapter).adapt(Mockito.any(Card.class));
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardController(eventBus, cardAdapter))
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
    public void save_a_card_with_categories() {
        Mockito.doReturn(new CardDTO("1", asList(getCategoryIdDTO("1"), getCategoryIdDTO("2")))).when(cardAdapter).adapt(Mockito.any(Card.class));
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardController(eventBus, cardAdapter))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"buy milk\", \"categories\": [{\"id\": \"1\"}, {\"id\": \"2\"}]}")
                .when();

        MockMvcResponse request = when.post("/api/v1/cards");

        request.then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo("1"))
                .body("categoryIds[0].id", equalTo("1"))
                .body("categoryIds[1].id", equalTo("2"))
        ;
    }

    @Test
    public void rename_a_card() {
        String cardIdValue = UUID.randomUUID().toString();
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardController(eventBus, cardAdapter))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"buy milks\"}")
                .when();

        MockMvcResponse request = when.put("/api/v1/rename/cards/"+cardIdValue);

        request.then()
                .statusCode(HttpStatus.OK.value());
        verify(eventBus).publish(new CardUpdatedEvent(CardCreated.CardID.from(cardIdValue), "buy milks"));
    }

    private CategoryIdDTO getCategoryIdDTO(String id) {
        CategoryIdDTO categoryIdDTO = new CategoryIdDTO();
        categoryIdDTO.id = id;
        return categoryIdDTO;
    }
}
