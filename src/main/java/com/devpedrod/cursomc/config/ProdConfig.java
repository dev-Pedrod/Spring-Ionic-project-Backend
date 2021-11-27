package com.devpedrod.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devpedrod.cursomc.services.DBService;
import com.devpedrod.cursomc.services.EmailService;
import com.devpedrod.cursomc.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	@Autowired
	DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!strategy.equals("create")) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
