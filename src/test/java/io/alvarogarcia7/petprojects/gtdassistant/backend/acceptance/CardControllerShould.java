package io.alvarogarcia7.petprojects.gtdassistant.backend.acceptance;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.Card;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardAdapter;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardController;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardDTO;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.category.CategoryIdDTO;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.created.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.CardUpdatedEvent;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestAsyncSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CardControllerShould {
    @Mock
    EventBus eventBus;

    @Mock
    CardAdapter cardAdapter;

    @Test
    public void save_a_card() {
        doReturn(new CardDTO("1", asList())).when(cardAdapter).adapt(any(Card.class));
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardController(eventBus, cardAdapter))
                .contentType(JSON)
                .accept(JSON)
                .body("{\"name\": \"buy milkb\"}")
                .when();

        MockMvcResponse request = when.post("/api/v1/cards");

        request.then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(JSON)
                .body("id", equalTo("1"));
    }

    @Test
    public void save_a_card_with_categories() {
        doReturn(new CardDTO("1", asList(getCategoryIdDTO("1"), getCategoryIdDTO("2")))).when(cardAdapter).adapt(any(Card.class));
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardController(eventBus, cardAdapter))
                .contentType(JSON)
                .accept(JSON)
                .body("{\"name\": \"buy milk\", \"categories\": [{\"id\": \"1\"}, {\"id\": \"2\"}]}")
                .when();

        MockMvcResponse request = when.post("/api/v1/cards");

        request.then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(JSON)
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
                .contentType(JSON)
                .accept(JSON)
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
