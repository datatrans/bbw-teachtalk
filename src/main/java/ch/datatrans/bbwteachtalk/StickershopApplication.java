package ch.datatrans.bbwteachtalk;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;

@SpringBootApplication
public class StickershopApplication {

	public static final String DATATRANS_USERNAME_PROPERTY = "datatransUsername";
	public static final String DATATRANS_PASSWORD_PROPERTY = "datatransPassword";

	public static void main(String[] args) {
		SpringApplication.run(StickershopApplication.class, args);
	}

	@Bean
	public RestTemplate rest(RestTemplateBuilder restTemplateBuilder) {
		// For the sake of the demo, grabbing the credentials from system or env properties
		String username = System.getProperty(DATATRANS_USERNAME_PROPERTY, System.getenv().get(DATATRANS_USERNAME_PROPERTY));
		String password = System.getProperty(DATATRANS_PASSWORD_PROPERTY, System.getenv().get(DATATRANS_PASSWORD_PROPERTY));

		return restTemplateBuilder.basicAuthentication(username, password).build();
	}

	// Needed in order to be able to connect to the H2 DB server from within the IDE
	@Bean(initMethod = "start", destroyMethod = "stop")
	@Profile("h2Server")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}
