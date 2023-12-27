package com.saison.omni.omnispringtemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition(servers = {
		@Server(url = "/${spring.application.name}", description = "Gateway URL"),
		@Server(url = "/", description = "Local URL")
})
@SpringBootApplication
@ComponentScan(basePackages = {"com.saison.omni.omnispringtemplate", "com.saison.omni.ehs"})
@EnableAutoConfiguration(exclude = RabbitAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
