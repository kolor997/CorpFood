package CorpFood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class})
public class Application {

    public static void main(String[] args)throws Exception {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public Docket corpFoodApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Welcome to CorpFood Inc. Swagger!")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/corpFood.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("CorpFood api")
                .title("CorpFood Inc.")
                .build();
    }
}
