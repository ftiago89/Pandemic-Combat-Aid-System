package com.felipemelo.pandemicsystem.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipemelo.pandemicsystem.domain.service.DBService;


@Configuration
@Profile("test")
public class ProfileTestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateTest() {
		dbService.instantiateTestDB();
		return true;
	}

}
