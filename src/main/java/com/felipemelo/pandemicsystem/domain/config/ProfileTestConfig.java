package com.felipemelo.pandemicsystem.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipemelo.pandemicsystem.domain.service.DBService;

/*Classe para configuração de profile de teste com banco de dados H2 rodando na memória.
 * para ativar este perfil, é necessário modificar o arquivo application.properties com a
 * linha: spring.profiles.active=test
 * 
 * A classe DBservice cria uma instancia de testes pré pronta no banco de dados.*/

@Configuration
@Profile("test")
public class ProfileTestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateTest() {
		//cria uma instancia de testes pre pronta na classe DBService.
		dbService.instantiateTestDB();
		return true;
	}

}
