package com.field.statistics.fieldservice;

import com.fasterxml.classmate.TypeResolver;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
//@Profile({"dev"})

public class SwaggerConfig {

    @Bean
    public Docket timeLineApi(TypeResolver typeResolver) {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .protocols(new HashSet<>(Arrays.asList("http", "https")))
            .ignoredParameterTypes(Principal.class)
            .apiInfo(ApiInfo.DEFAULT)
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(java.sql.Date.class)
            .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
            .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
            .useDefaultResponseMessages(false);

        docket = docket.select()
//            .apis(RequestHandlerSelectors.basePackage("de.elinvar.timeline.rest"))
            .paths(PathSelectors.any())
            .build();

        return docket;
    }
}