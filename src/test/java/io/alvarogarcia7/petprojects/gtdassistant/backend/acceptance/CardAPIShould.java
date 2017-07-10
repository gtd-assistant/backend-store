package io.alvarogarcia7.petprojects.gtdassistant.backend.acceptance;

import io.alvarogarcia7.petprojects.gtdassistant.backend.DataSourceConfig;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardAdapter;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardCreated;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardWriteRepository;
import io.alvarogarcia7.petprojects.gtdassistant.backend.card.CardsController;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import(DataSourceConfig.class)
@RunWith(SpringRunner.class)
public class CardAPIShould {
    @Mock
    CardWriteRepository cardWriteRepository;


    @Test
    public void insert_a_card_creation_event() {
        given()
                .standaloneSetup(new CardsController(cardWriteRepository, new CardAdapter()))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"buy milk\"}")
                .when()
                .post("/cards").
                then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo(1));

        verify(cardWriteRepository).save(new CardCreated("buy milk"));
    }
}
