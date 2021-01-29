package com.felipemelo.pandemicsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.felipemelo.pandemicsystem.api.controller.RelatoriosController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RelatoriosController.class)
public class RelatoriosControllerTests {
	
	@Test
	public void CheckMaiorQueNoventa() {
		
	}

}
