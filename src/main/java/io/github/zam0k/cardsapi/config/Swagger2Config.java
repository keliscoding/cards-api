package io.github.zam0k.cardsapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@EnableWebMvc
@Configuration
public class Swagger2Config {

    public static final String CARDS_TAG = "cards";
    public static final String ORIGIN_TAG = "origins";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.zam0k.cardsapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo())
                .useDefaultResponseMessages(false)
                .tags(new Tag(CARDS_TAG, "Card management endpoints"),
                        new Tag(ORIGIN_TAG, "Origin management endpoints"));
    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                "Cards API REST",
                "management cards API REST based on SuperTrunfo",
                "1.0",
                "Terms of Service",
                new Contact("Kelly Castelo", "https://github.com/zam0k",
                        "kellyplcastelo@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<>()
        );
    }
}
