package com.felipemelo.pandemicsystem.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipemelo.pandemicsystem.domain.service.DBService;

/*Classe para configuração de profile de dev com banco de dados Mysql rodando localmente.
 * para ativar este perfil, é necessário modificar o arquivo application.properties com a
 * linha: spring.profiles.active=dev
 * 
 * A classe DBservice instancia uma instancia de testes pré pronta no banco de dados.*/

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
		
		/*caso o campo spring.jpa.hibernate.ddl-auto= do arquivo application-dev.properties
		 *esteja como "create" essa instancia será criada.*/
		dbService.instantiateTestDB();
		return true;
	}
}
