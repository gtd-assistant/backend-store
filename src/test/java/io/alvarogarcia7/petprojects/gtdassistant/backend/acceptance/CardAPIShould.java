package io.alvarogarcia7.petprojects.gtdassistant.backend.acceptance;

import io.alvarogarcia7.petprojects.gtdassistant.backend.EventBus;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.*;
import io.alvarogarcia7.petprojects.gtdassistant.backend.events.CardUpdatedEvent;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestAsyncSender;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CardAPIShould {

    @LocalServerPort
    int port;

    @Mock
    EventBus eventBus;

    @Mock
    CardAdapter cardAdapter;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void save_a_card() {
        Mockito.doReturn(new CardDTO("1", Arrays.asList())).when(cardAdapter).adapt(Mockito.any(Card.class));

        ResponseEntity<CardDTO> response = testRestTemplate.postForEntity("http://localhost:" + port + "/api/v1/cards", "{\"name\": \"forbidden\"}", CardDTO.class);

//        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void save_a_card_with_categories() {
        Mockito.doReturn(new CardDTO("1", Arrays.asList(getCategoryIdDTO("1"), getCategoryIdDTO("2")))).when(cardAdapter).adapt(Mockito.any(Card.class));
        MockMvcRequestAsyncSender when = given()
                .standaloneSetup(new CardsController(eventBus, cardAdapter))
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

    private CategoryIdDTO getCategoryIdDTO(String id) {
        CategoryIdDTO categoryIdDTO = new CategoryIdDTO();
        categoryIdDTO.id = id;
        return categoryIdDTO;
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

        MockMvcResponse request = when.put("/api/v1/rename/cards/" + cardIdValue);

        request.then()
                .statusCode(HttpStatus.OK.value());
        verify(eventBus).publish(new CardUpdatedEvent(CardCreated.CardID.from(cardIdValue), "buy milks"));
    }
}
