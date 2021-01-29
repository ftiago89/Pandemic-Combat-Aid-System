package com.felipemelo.pandemicsystem.api.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.felipemelo.pandemicsystem.api.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("API - Pandemic Combat Aid System",
				"Esta API foi implementada como desafio para processo seletivo da Phoebus Tecnologia", "Versão 1.0",
				"",
				new Contact("Felipe Melo", "https://github.com/ftiago89/Pandemic-Combat-Aid-System", "ftiagomel89@gmail.com"), "", "",
				Collections.emptyList() // Vendor Extensions
		);
	}
}
