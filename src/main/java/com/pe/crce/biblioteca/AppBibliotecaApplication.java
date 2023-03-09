package com.pe.crce.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class AppBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBibliotecaApplication.class, args);
	}
	
	@Bean
	public OpenAPI info() {
		return new OpenAPI()
				.info(new Info()
						.title("app-biblioteca")
						.description("API Biblioteca para el canal de Youtube")
						.contact(new Contact()
								.email("ctc.tucno@gmail.com")));
	}

}
