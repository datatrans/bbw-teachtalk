package ch.datatrans.bbwtechtalk;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;

@SpringBootApplication
public class BbwTechtalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbwTechtalkApplication.class, args);
	}

	@Bean
	public RestTemplate rest(RestTemplateBuilder restTemplateBuilder) {

		// For the sake of the demo, grabbing the credentials from system properties
		String username = System.getProperty("datatransUsername");
		String password = System.getProperty("datatransPassword");

		return restTemplateBuilder.basicAuthentication(username, password).build();
	}

	/*
		Needed in order to be able to connect to the H2 DB server from within the IDE
	 */
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}
