package ch.datatrans.bbwtechtalk;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class BbwTechtalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbwTechtalkApplication.class, args);
	}

	/*
		Needed in order to be able to connect to the H2 DB server from within the IDE
	 */
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}
