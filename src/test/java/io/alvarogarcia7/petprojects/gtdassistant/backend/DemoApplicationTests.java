package io.alvarogarcia7.petprojects.gtdassistant.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(DataSourceConfig.class)
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
