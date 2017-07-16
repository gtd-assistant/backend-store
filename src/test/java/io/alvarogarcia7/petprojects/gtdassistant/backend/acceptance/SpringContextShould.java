package io.alvarogarcia7.petprojects.gtdassistant.backend.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(io.alvarogarcia7.petprojects.gtdassistant.backend.configuration.DataSourceConfig.class)
public class SpringContextShould {
    @Test
    public void load_successfully() {
    }

}
