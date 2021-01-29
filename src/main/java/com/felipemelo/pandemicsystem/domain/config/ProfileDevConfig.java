package com.felipemelo.pandemicsystem.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipemelo.pandemicsystem.domain.service.DBService;

@Configuration
@Profile("dev")
public class ProfileDevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateTest() {
		if (!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateTestDB();
		return true;
	}
}
