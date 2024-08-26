package org.jsp.se.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI usersMicroserviceOpenAPI() {
		Server localhost = new Server();
		localhost.setUrl("http://nature.us-east-1.elasticbeanstalk.com");
		localhost.setDescription("Development environment");
		Contact contact = new Contact();
		contact.setEmail("abc@gmail.com");
		contact.setName("TMS");
		contact.setUrl("http://nature.us-east-1.elasticbeanstalk.com/");
		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
		Info info = new Info().title("Task Management System").version("1.0")
				.contact(contact).description("This API exposes endpoints to manage Application.")
				.termsOfService("http://nature.us-east-1.elasticbeanstalk.com/terms").license(mitLicense);
		return new OpenAPI().info(info).servers(List.of(localhost));
	}

}
