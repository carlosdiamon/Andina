package edu.unbosque.adiana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class AndinaExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndinaExchangeApplication.class, args);
	}

}