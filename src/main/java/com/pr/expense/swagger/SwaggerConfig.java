package com.pr.expense.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by blueoptima on 01/05/15.
 */
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket plugin() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pr.expense.controller"))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/api")
				.genericModelSubstitutes(ResponseEntity.class);
	}
}
