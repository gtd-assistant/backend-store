package io.alvarogarcia7.petprojects.gtdassistant.backend;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@org.springframework.context.annotation.Configuration
@ComponentScan({ "io.alvarogarcia7.petprojects.gtdassistant.backend" })
public class WebConfig extends WebMvcConfigurerAdapter {
}
