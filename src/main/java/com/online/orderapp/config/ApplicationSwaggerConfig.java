package com.online.orderapp.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;



@Configuration
public class ApplicationSwaggerConfig {
	
	@Bean
	OpenAPI openApi() {
		Server localhost = new Server();
		localhost.setUrl("http://localhost:8080");
		localhost.setDescription("Local Environment");
		
		Contact contact = new Contact();
		contact.setEmail("ujjwalsharma.ece@gmail.com");
		contact.setName("Ujjwal");
		
		Info info = new Info().title("Online Food Order Application")
				.version("1.0")
				.contact(contact)
				.description("This documentation exposes API endpoints to manage the food application");
		
		return new OpenAPI().info(info).servers(List.of(localhost));
		
		
		
	}

}
